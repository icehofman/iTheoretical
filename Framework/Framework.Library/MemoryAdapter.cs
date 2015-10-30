using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Caching;

namespace Framework.Cache
{
    internal class MemoryAdapter : CacheManager
    {
        private MemoryCache _memoryCache;
        private int _minutesToExpire;
        private ExpirationType _expirationType;

        private class NullObject
        {
        }

        public MemoryAdapter(string name)
            : this(name, 30, ExpirationType.Sliding)
        {
        }

        public MemoryAdapter(string name, int minutesToExpire, ExpirationType expirationType)
        {
            _memoryCache = new MemoryCache(name);
            _expirationType = expirationType;
            _minutesToExpire = minutesToExpire;
        }

        public MemoryAdapter(CacheSettings cacheSettings)
        {
            CacheSettings = cacheSettings;
            _memoryCache = new MemoryCache(cacheSettings.Name);
            _expirationType = cacheSettings.ExpirationType;
            _minutesToExpire = cacheSettings.TimeToLive;
        }

        private CacheItemPolicy ResolveItemPolicy()
        {
            var itemPolicy = new CacheItemPolicy() { Priority = CacheItemPriority.Default };
            if (_minutesToExpire < 0)
                return itemPolicy;
            switch (_expirationType)
            {
                case ExpirationType.Sliding:
                    itemPolicy.SlidingExpiration = TimeSpan.FromMinutes(_minutesToExpire >= 0 ? _minutesToExpire : 0);
                    break;
                case ExpirationType.Absolute:
                    itemPolicy.AbsoluteExpiration = DateTimeOffset.Now.AddMinutes(_minutesToExpire >= 0 ? _minutesToExpire : 0);
                    break;
            }

            return itemPolicy;
        }

        public override object Get(string key)
        {
            var value = _memoryCache.Get(key);

            if (value is NullObject)
                return null;
            else
                return value;
        }

        public override void Set(string key, object value)
        {
            if (value == null)
                _memoryCache.Set(key, new NullObject(), ResolveItemPolicy());
            else
                _memoryCache.Set(key, value, ResolveItemPolicy());
        }

        public override void Set(string key, object value, DateTimeOffset expiration)
        {
            _memoryCache.Set(key, value, new CacheItemPolicy { AbsoluteExpiration = expiration, Priority = CacheItemPriority.Default });
        }
        public override bool Contains(string key)
        {
            return _memoryCache.Contains(key);
        }

        public override List<string> GetKeys()
        {
            return _memoryCache.Select(mc => mc.Key).ToList();
        }

        public override void Remove(string key)
        {
            if (Contains(key))
                _memoryCache.Remove(key);
        }

        public override List<object> GetMapReducedView(string mapReduce)
        {
            throw new NotImplementedException();
        }

        public override List<object> GetKeys(string design, string view, string[] keys)
        {
            throw new NotImplementedException();
        }

        public override string GetView(string design, string view)
        {
            throw new NotImplementedException();
        }
    }
}

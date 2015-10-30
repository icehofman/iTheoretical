using System;
using System.Collections.Generic;

namespace Framework.Cache
{
    public abstract class CacheManager
    {
        public CacheSettings CacheSettings;
        public abstract object Get(string key);
        public abstract bool Contains(string key);
        public abstract void Set(string key, object value);
        public abstract void Set(string key, object value, DateTimeOffset expiration);
        public abstract void Remove(string key);
        public abstract List<string> GetKeys();
        public abstract List<object> GetKeys(string design, string view, string[] keys);
        public abstract List<object> GetMapReducedView(string mapReduce);
        public abstract string GetView(string design, string view);

        public object this[string key]
        {
            get
            {
                return Get(key);
            }
            set
            {
                Set(key, value);
            }
        }
    }
}

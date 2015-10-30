using System.Collections.Concurrent;

namespace Framework.Cache
{
    public class CacheManagerFactory
    {
        private static ConcurrentBag<CacheManager> _caches = new ConcurrentBag<CacheManager>();

        public CacheManagerFactory()
        {
        }

        public static CacheManager GetCacheManager(string name)
        {
            CacheSettings cacheSettings = new CacheSettings();

            cacheSettings.CacheType = CacheType.Memory;
            cacheSettings.ExpirationType = ExpirationType.Absolute;
            cacheSettings.Name = name;
            cacheSettings.TimeToLive = 3600;

            return GetCacheManager(cacheSettings);
        }

        public static CacheManager GetCacheManager(CacheSettings cacheSettings)
        {
            CacheManager cache = null;

            switch (cacheSettings.CacheType)
            {
                case CacheType.Memory:
                    cache = GetInstance(cacheSettings, new MemoryAdapter(cacheSettings));
                    break;
                default:
                    cache = GetInstance(cacheSettings, new MemoryAdapter(cacheSettings));
                    break;
            }

            return cache;
        }

        private static CacheManager GetInstance(CacheSettings cacheSettings, CacheManager newAdapter)
        {
            CacheManager instance = null;

            if (_caches.Count > 0)
                foreach (var cache in _caches)
                    if (cache.CacheSettings.Name.Equals(cacheSettings.Name))
                        instance = cache;

            if (instance == null)
            {
                instance = newAdapter;
                _caches.Add(newAdapter);
            }

            return instance;
        }
    }
}

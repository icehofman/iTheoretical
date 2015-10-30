
namespace Framework
{
    public static class SystemUtil
    {
        public static string DefaultDirectoryInput
        {
            get 
            {
                return System.Configuration.ConfigurationManager.AppSettings["defaultDirectoryInput"];
            }           
        }

        public static string DefaultDirectoryOutput
        {
            get
            {
                return System.Configuration.ConfigurationManager.AppSettings["defaultDirectoryOutput"];
            }
        }
    }
}

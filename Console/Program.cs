using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using iTheoretical.Business;
using Framework.Log;
namespace iTheoretical
{
    class Program
    {
        static void Main(string[] args)
        {
            log4net.Config.XmlConfigurator.Configure();

            try
            {
                var importacao = new ImportingFile();
                var container = importacao.ProcessFiles();
                var exportacao = new ExportingFiles();
                exportacao.ProcessFiles(container);
            }
            catch (Exception e)
            {
                Logger.Error(e);
            }
        }
    }
}

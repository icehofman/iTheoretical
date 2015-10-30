using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Framework;
using System.IO;
using Framework.Report;
using iTheoretical.Business.Importing;

namespace iTheoretical.Business
{
    public class ExportingFiles
    {
        public void ProcessFiles(FileContainer container) 
        {
            var directoryPath = SystemUtil.DefaultDirectoryOutput;
            var directoryInfo = new DirectoryInfo(directoryPath);

            foreach (var item in container.files)
            {
                try
                {
                    var path = Path.Combine(directoryPath, item.Name + ".done.dat");
                    if (File.Exists(path))
                        File.Delete(path);

                    using (FileStream fs = File.Create(path))
                    {
                        Byte[] title = new UTF8Encoding(true).GetBytes(item.GenerateReport());
                        fs.Write(title, 0, title.Length);
                        fs.Close();
                    }
                }
                catch (Exception e)
                {
                    Framework.Log.Logger.Error(e); 
                }
            }
           
        }

    }
}

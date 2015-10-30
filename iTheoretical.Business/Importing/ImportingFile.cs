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
    public class ImportingFile
    {
        #region ProcessFiles
        public FileContainer ProcessFiles()
        {
            FileContainer container = new FileContainer();

            var directoryPath = SystemUtil.DefaultDirectoryInput;
            var directoryInfo = new DirectoryInfo(directoryPath);
            var files = directoryInfo.GetFiles("*.dat");

            ParallelOptions parOpts = new ParallelOptions();
            parOpts.MaxDegreeOfParallelism = Environment.ProcessorCount;

            //Processa paralelamente os arquivos
            Parallel.ForEach(files, parOpts, (currentFile) =>
            {
                var item = FileFactory.CreateInstance();
                item.Name = currentFile.Name ;

                try
                {
                    ReadFile(currentFile, item);
                    item.Summarize();
                }
                catch (Exception e)
                {
                    Framework.Log.Logger.Error(e); 
                    item.AddError(e.ToString());
                }
                finally
                {
                    container.Add(item);
                }
            });

            return container;
        }
        #endregion

        #region ReadFile
        private void ReadFile(FileInfo file, Report item)
        {
            try
            {
                var parser = new FileParser();
                const Int32 BufferSize = 128;
                using (var fileStream = file.OpenRead())
                {
                    using (var streamReader = new StreamReader(fileStream, Encoding.UTF8, true, BufferSize))
                    {
                        String line;
                        while ((line = streamReader.ReadLine()) != null)
                        {
                            parser.ProcessLine(line, item);
                        }
                        streamReader.Close();
                    }
                    fileStream.Close();
                }
            }
            catch (Exception)
            {
                throw;
            }
        }
        #endregion

    }
}

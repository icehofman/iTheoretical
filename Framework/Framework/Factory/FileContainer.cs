using System.Collections.Generic;

namespace Framework.Report
{
    public class FileContainer
    {
        public List<Report> files { get; set; }
        
        public FileContainer()
        {
            files = new List<Report>();
        }

        public void Add(Report file)
        {
            if (file != null)
                files.Add(file);
        }
    }
}

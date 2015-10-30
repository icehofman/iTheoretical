using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace GTF.Business
{
    public class ImportingValidator
    {   
        #region Constructor
        public ImportingValidator()
        {
        }
        #endregion

        #region isFileValid
        public bool isFileValid()
        {
            bool isOK = false;

            try
            {
               
            }
            catch (Exception e)
            {
                Console.WriteLine(e.ToString());
                Framework.Log.Logger.Error(e);
            }

            return isOK;
        }
        #endregion

   
    }
}

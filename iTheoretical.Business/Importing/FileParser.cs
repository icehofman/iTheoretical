using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Framework;
using Framework.Report;
using Framework.Custumer;
using Framework.Sales;
using Framework.Salesman;

namespace iTheoretical.Business.Importing
{
    public class FileParser
    {
        #region ProcessLine
        public void ProcessLine(string line, Report report)
        {
            if (!string.IsNullOrEmpty(line))
            {
                string[] itens = line.Split('ç');

                if (itens.Length > 1)
                {
                    switch (itens[0])
                    {
                        case "001":
                            ParserSalesman(report, itens);
                            break;
                        case "002":
                            ParserCustumer(report, itens);
                            break;
                        case "003":
                            ParserSales(report, itens);
                            break;
                    }
                }
                else
                    throw new ApplicationException("Erro no processamento do arquivo.");
            }
        }
        #endregion

        #region ParserSalesman
        /// <summary>
        /// Parser da linha 001
        /// </summary>
        /// <example>Layout Esperado 001çCPFçNameçSalary</example>
        private void ParserSalesman(Report report, string[] itens)
        {
            if (itens.Length == 4)
            {
                var salesman = new Salesman() { CPF = itens[1], Name = itens[2], Salary = itens[3] };
                report.AddSalesman(salesman);
            }
            else
            {
                throw new ApplicationException("Erro no processamento do arquivo.");
            }
        }
        #endregion
        
        #region ParserCustumer
        /// <summary>
        /// Parser da linha 002
        /// </summary>
        /// <example>Layout Esperado 002çCNPJçNameçBusiness Area</example>
        private void ParserCustumer(Report report, string[] itens)
        {
            if (itens.Length == 4)
            {
                var custumer = new Custumer() { CNPJ = itens[1], Name = itens[2], BusinessArea = itens[3]};
                report.AddCustumer(custumer);
            }
            else
            {
                throw new ApplicationException("Erro no processamento do arquivo.");
            }        
        }
        #endregion

        #region ParserSales
        /// <summary>
        /// Parser da linha 003
        /// </summary>
        /// <example>Layout Esperado 003çSale IDç[Item ID-Item Quantity-Item Price]çSalesman name</example>
        private void ParserSales(Report report, string[] itens)
        {
            if (itens.Length == 4)
            {
                Sales sale = new Sales(itens[1], itens[3]);
                this.ParserSalesItens(sale, itens[2]);
                report.AddSale(sale);
            }
            else
            {
                throw new ApplicationException("Erro no processamento do arquivo.");
            }
        }
        #endregion

        #region ParserSalesItens
        /// <summary>
        /// Parser da linha 003 / Sales Item
        /// </summary>
        /// <example>Layout Esperado [Item ID-Item Quantity-Item Price]</example>
        private void ParserSalesItens(Sales sale, string saleItem)
        {
            var itens = saleItem.Replace("[", "").Replace("]", "").Split(',');
            if (itens.Length > 0)
            {
                foreach (var item in itens)
                {
                    var auxItem = item.Split('-');
                    int qtdy = 0;
                    bool isvalid = int.TryParse(auxItem[1], out qtdy);
                    if(!isvalid)
                        throw new ApplicationException("Erro no processamento do arquivo.");
                    double price = 0;
                    isvalid = Double.TryParse(auxItem[2], out price);
                    if (!isvalid) 
                        throw new ApplicationException("Erro no processamento do arquivo.");

                    SalesItem si = new SalesItem() { ID = auxItem[0], Quantity = qtdy, Price = price};
                    sale.AddSalesItem(si);
                }
            }
            else
            {
                throw new ApplicationException("Erro no processamento do arquivo.");
            }
        }
        #endregion
    }
}

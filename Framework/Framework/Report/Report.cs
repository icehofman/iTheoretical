using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Framework.Report
{
    public class Report
    {
        #region Constructor and Properties
        private List<Custumer.Custumer> Custumers { get; set; }
        private List<Sales.Sales> Sales { get; set; }
        private List<Salesman.Salesman> Salesman { get; set; }
        private List<string> Errors { get; set; }
        private StringBuilder sb { get; set; }
        public string Name { get; set; }

        public Report()
        {
            this.Custumers = new List<Custumer.Custumer>();
            this.Sales = new List<Sales.Sales>();
            this.Salesman = new List<Salesman.Salesman>();
            this.Errors = new List<string>();
            this.sb = new StringBuilder();
            
        }
        #endregion

        #region Summarize
        public void Summarize()
        {
            this.sb.Append(GetAmountClients());
            this.sb.Append(Environment.NewLine);
            this.sb.Append(GetAmountSalesman());
            this.sb.Append(Environment.NewLine);
            this.sb.Append(GetWorstSalesman());
            this.sb.Append(Environment.NewLine);
            this.sb.Append(GetMostExpensiveSale());
            this.sb.Append(Environment.NewLine);
        }
        #endregion

        #region AddSalesman
        public void AddSalesman(Salesman.Salesman salesman)
        {
            this.Salesman.Add(salesman);
        }
        #endregion

        #region AddSale
        public void AddSale(Sales.Sales sale)
        {
            this.Sales.Add(sale);
        }
        #endregion

        #region AddCustumer
        public void AddCustumer(Custumer.Custumer custumer)
        {
            this.Custumers.Add(custumer);
        }
        #endregion

        #region AddError
        public void AddError(string erro)
        {
            this.Errors.Add(erro);
        }
        #endregion

        #region GetAmountClients
        private string GetAmountClients()
        { 
           return "Amount of Clients = " + this.Custumers.Count;        
        }
        #endregion

        #region GetAmountSalesman
        private string GetAmountSalesman()
        {
            return "Amount of Salesman = " + this.Salesman.Count;
        }
        #endregion

        #region GetWorstSalesman
        private string GetWorstSalesman()
        {
            string strReport = "";
            double somaBySalesman = 0;
            foreach (var item in Salesman)
            {
                double auxSoma = 0;
                var sales = this.Sales.Where(x => x.SaleMan == item.Name);
                foreach (var sale in sales)
                {
                    auxSoma += sale.Itens.Sum(m => m.Price * m.Quantity);
                }
                if (somaBySalesman < auxSoma)
                {
                    somaBySalesman = auxSoma;
                    strReport = "Worst salesman ever = " + item.Name;
                }
            }
            return strReport;
        }
        #endregion

        #region GetMostExpensiveSale
        private string GetMostExpensiveSale()
        {
            string strReport = "";
            double maxSale = 0;
            foreach (var item in Sales)
            {
                var soma = item.Itens.Sum(m => m.Price * m.Quantity);
                if (maxSale < soma)
                {
                    maxSale = soma;
                    strReport = "ID of the most expensive sale is " + item.SaleId;
                }

            }
            return strReport;
        }
        #endregion

        #region GenerateReport
        public string GenerateReport()
        {
            return this.sb.ToString();
        }
        #endregion

    }
}

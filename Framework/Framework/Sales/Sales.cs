using System.Collections.Generic;

namespace Framework.Sales
{
    public class Sales : BaseItem
    {
        public string SaleMan { get; set; }
        public string SaleId { get; set; }
        public List<SalesItem> Itens { get; set; }

        public Sales(string saleId,string saleMan)
        {
            this.SaleId = saleId;
            this.SaleMan = saleMan;
            this.Itens = new List<SalesItem>();
        }

        public void AddSalesItem(SalesItem salesItem)
        {
            this.Itens.Add(salesItem);
        }
    }
}

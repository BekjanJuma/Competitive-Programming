using System;
using System.Text;
using System.Text.RegularExpressions;
using System.Collections.Generic;
using System.Linq;

public class Sum
{
    private static void Main()
    {
        var sum = new Sum();
        sum.p1030();
    }

public void p1030() {
         string s = Console.ReadLine().Trim();
         s = Console.ReadLine();
         s = Console.ReadLine();
         s = Console.ReadLine();
         var ss = extract(s);
         double phi1 = Math.PI*(rem(ss[0]) * 3600 + rem(ss[1]) * 60 + rem(ss[2]) + 0.0) / (90 * 3600)/2;
         if (ss[3] == "SL") phi1 = -phi1;
         s = Console.ReadLine();
         ss = extract(s);
         double lambda1 = Math.PI * (rem(ss[0]) * 3600 + rem(ss[1]) * 60 + rem(ss[2]) + 0.0) / (180 * 3600);
         if (ss[3] == "WL.") lambda1 = -lambda1;
         s = Console.ReadLine();
         s = Console.ReadLine();
         ss = extract(s);
         double phi2 = Math.PI * (rem(ss[0]) * 3600 + rem(ss[1]) * 60 + rem(ss[2]) + 0.0) / (90 * 3600)/2;
         if (ss[3] == "SL") phi2 = -phi2;
         
         s = Console.ReadLine();
         ss = extract(s);
         double lambda2 = Math.PI * (rem(ss[0]) * 3600 + rem(ss[1]) * 60 + rem(ss[2]) + 0.0) / (180 * 3600);
         if (ss[3] == "WL.") lambda2 = -lambda2;
         
         s = Console.ReadLine();

         double delta = Math.Abs(lambda1-lambda2);

         double rho = Math.Acos(Math.Sin(phi1)*Math.Sin(phi2) + Math.Cos(phi1)*Math.Cos(phi2)*Math.Cos(delta))/2;

         decimal res = decimal.Round(6875 * (decimal)rho, 2, MidpointRounding.AwayFromZero);


         Console.WriteLine("The distance to the iceberg: " + res + " miles. ");
         if (res < 99.995m)
            Console.WriteLine("DANGER!");
      }
 string[] extract(string line) {
         int pos1 = line.IndexOf('^');
         int pos2 = line.IndexOf('\'');
         int pos3 = line.IndexOf('\"');
         string[] res = { line.Substring(0, pos1), line.Substring(pos1 + 1, pos2-pos1-1), line.Substring(pos2 + 1, pos3-pos2-1), line.Substring(pos3 + 2) };
         if (line.StartsWith("and")) {
            res[0] = line.Substring(4, pos1-4);
         }
         return res;
      }
      int rem(string s) {
if (s.Length == 1) return int.Parse(s.ElementAt(0) + "");
         if (s.ElementAt(0) == '0') return int.Parse(s.ElementAt(1) + "");
         else return int.Parse(s);
      }
}
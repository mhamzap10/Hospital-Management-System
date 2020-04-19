
package ManagementSystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

public class DataBase {
    public String[]getData(int id)
    {
        String Name="", Nic="", Disease="", Trt="";
        int charges= 0;
        String[] Data = new String[5];
        Connection con = setConnection();
        try{
            Statement st  = con.createStatement();
            ResultSet rs =  st.executeQuery("SELECT * FROM Patient Where ID = "+id);
           while(rs.next())
           {
               Nic = rs.getString("NicNo");
               Name = rs.getString("PName");
               Disease = rs.getString("Disease");
               Trt = rs.getString("Treatement");
               charges = rs.getInt("Payment");
           }
        }catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
        Data[0]=Nic;
        Data[1]=Name;
        Data[2] = Disease;
        Data[3] = Trt;
        Data[4] = String.valueOf(charges);
        return Data;
    }
       public static Connection setConnection()
               {
                   String url = "jdbc:ucanaccess://D://hamza//assignment//NetBeansProjects//DMS//DMS1.accdb";
                   Connection con = null;
                   try
                   {
                       con = DriverManager.getConnection(url);
                       System.out.println("Connection Established Succeesfull");
                   }
                   catch(Exception sql)
                   {
                       System.out.println(sql);
                   }
                  return con; 
               }
       public int getID()
       {
           Connection con = setConnection();
            int k = 0;
           try
           {
           Statement st = con.createStatement();
           ResultSet rs = st.executeQuery("SELECT * FROM Patient");
           while(rs.next())
           {
               k++;
           }
           rs.close();
           st.close();
           }
           catch (Exception e)
           {
               System.out.println("Error Occuer");
           }
           return k+1;
       }
       public int getAppointmentNumber()
       {
           Connection con = setConnection();
            int k = 0;
            String pattern = "dd-MM-yyyy";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            String date = simpleDateFormat.format(new Date());
           try
           {
           Statement st = con.createStatement();
           ResultSet rs = st.executeQuery("SELECT * FROM Patient Where VisitingDate = '"+date+"'");
           while(rs.next())
           {
               k++;
           }
           rs.close();
           st.close();
           }
           catch (Exception e)
           {
               System.out.println("Error Occuer");
           }
           return k+1;
       }
       public void AddTreatment(int id, String Dis, String trt, int Charges)
       {
           Connection con = setConnection();
           try
           {
           Statement st = con.createStatement();
           st.executeUpdate("UPDATE Patient set Disease = '"+Dis+"' Where ID = "+id);
           st.executeUpdate("UPDATE Patient set Treatement = '"+trt+"' Where ID = "+id);
           st.executeUpdate("UPDATE Patient set Payment = "+Charges+" Where ID = "+id);
           st.close();
           con.close();
           JOptionPane.showMessageDialog(null,"Data Saved");
           }
           catch (Exception e)
           {
               JOptionPane.showMessageDialog(null, e);
           }
       }
       public void AddInfoReceptionist(String Nic, String Name , String Age,String ap)
       {
           Connection con = setConnection();
           String pattern = "dd-MM-yyyy";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            String date = simpleDateFormat.format(new Date());
           try
           {
           Statement st = con.createStatement();
           st.executeUpdate("INSERT INTO Patient(NicNo, PName, PAge,VisitingDate,AppointmentNum)Values('"+Nic+"','"+Name+"','"+Age+"','"+date+"','"+ap+"')");
           JOptionPane.showMessageDialog(null, "Data Saved SuccessFully");
           }catch(Exception e){}
           
       }
       public static void main(String [] args)
       {
           //Connection con = setConnection();
           //AddInfoReceptionist("442-987-987","Mehr Muhammad Hamza","20");
           //System.out.println("ID = "+getID());
          
       }
}

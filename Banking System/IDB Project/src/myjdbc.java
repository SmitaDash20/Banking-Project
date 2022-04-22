import java.sql.*;
import java.io.*;
public class myjdbc {

	public static void main(String[] args) throws IOException{
		
		Connection con=null;
		Statement stmt=null,stmt1=null;
		int ch,m,n;
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			
			String conurl="jdbc:mysql://localhost:3306/mysql";
			con=DriverManager.getConnection(conurl,"root","12345");
			stmt=con.createStatement();
			do
			{
	
			System.out.println("\n\n***** Banking Management System *****");	
			System.out.println("1. Show Customer Records");
			System.out.println("2. Add Customer Record");
			System.out.println("3. Delete Customer Record");
			System.out.println("4. Update Customer Record");
			System.out.println("5. Show Account Details of a Customer");
			System.out.println("6. Show Loan Details of a Customer");
			System.out.println("7. Deposit Money");
			System.out.println("8. Withdraw money");
			System.out.println("9. EXIT");
			
			System.out.println("Enter your choice(1-9):");
			BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			ch = Integer.parseInt(br.readLine());
			//System.out.println(ch);
				
			switch(ch)
			{
			case 1:
			//display_record();
			m=0;	
			String sqlstmt="select * FROM assignment3.customer";
			ResultSet rs=stmt.executeQuery(sqlstmt);  
		    	while(rs.next()) 
			{ 
			m++;
		   	System.out.print(String.format("%-6s\t",rs.getString(1)));
			System.out.print(String.format("%-20s\t",rs.getString(2)));
			System.out.print(String.format("%-11s\t",rs.getString(3)));
			System.out.print(String.format("%-10s\n",rs.getString(4)));
			}
			System.out.println(m+ "row displayed");
			//stmt.close();
			
			break;
			
			case 2:
			//add_customer_record()	
			System.out.println("Enter customer number");
			String cust_no=br.readLine();
			System.out.println("Enter name");
			String name=br.readLine();
			System.out.println("Enter phone number");
			long phno=Long.parseLong(br.readLine());
			System.out.println("Enter City");
			String city=br.readLine();
			
			stmt=con.createStatement();
			String ins="insert into assignment3.customer values('"+cust_no+"','"+name+"',"+phno+",'"+city+"')";
			n=stmt.executeUpdate(ins);
			System.out.println(n+"Row inserted");
			//stmt.close();
			break;

			case 3:
				//delete customer record()
			System.out.println("Enter the customer number to be deleted ");
			cust_no=br.readLine();
			String dele="delete from customer where cust_no='"+cust_no+"'";
			n=stmt.executeUpdate(dele);
			System.out.println(n+ "row deleted");
			
			break;
			
			case 4:
			//update_customer_record();
				System.out.println("Enterthecustomernumberfor updation");
						String cust_no2 = br.readLine().toUpperCase();
						String updateStr;
						int n2=0,choice=0;do{
						System.out.println("Enterpropertytoupdate(1-3):\n1:Name\n2:Phone\n3:City\n0:EXIT");
						// System.out.println("Enter your choice:");
						choice=Integer.parseInt(br.readLine());switch(choice){
						case 0:
							break;
						case 1:
						System.out.println("Enter the updated NAME :");
						String uName =br.readLine().toUpperCase();
						updateStr="UPDATECUSTOMER SETNAME='"+uName+"'WHERE CUST_NO='"+cust_no2+"'";
						n2 =
						stmt.executeUpdate(updateStr);System.out.println
						(n2+" no of rows updated!\n");
						break;
						case 2:
						System.out.println("Enter the updated Phone number :");
						long uPh =Long.parseLong(br.readLine());
						updateStr="UPDATECUSTOMERS ETPHONE_NO='"+uPh+"'WHERE CUST_NO='"+cust_no2+"'";
						n2 = stmt.executeUpdate(updateStr);
						System.out.println(n2+" no of rows updated!\n");
						break;
						case 3:
						System.out.println("Enter the updated city name :");
						String uCity =br.readLine().toUpperCase();
						updateStr="UPDATECUSTOMER SETCITY='"+uCity+"'WHERE CUST_NO='"+cust_no2+"'";
						n2 =stmt.executeUpdate(updateStr);
						System.out.println(n2+" no of rows updated!\n");
						break;
						default:
						System.out.println("Invalidchoice!");
						}
						}while(choice!=0);
						if(n2!=0){
						String sqlstr="SELECT * FROM CUSTOMER ORDER BY CUST_NO";
						rs=stmt.executeQuery(sqlstr);
						System.out.printf("%-7s|%-20s|%-15s|%10s\n","ID","NAME","NUMBER","CITY");
						System.out.println("---------------------------------------------------------");
						while(rs.next())
						System.out.printf("%-7s|%-20s|%-15d|%10s\n",rs.getString(1),rs.getString(2),rs.getLong(3),rs.getString(4));
						}else{
						System.out.println("InvalidCustomerNumber");
						}
						break;
			case 5:
				System.out.println("Enterthecustomernumbertoshow details");
						String cust_no3=br.readLine().toUpperCase();
						String sqlstr="SELECTA.ACCOUNT_NO,A.TYPE,A.BALANCE,A.BRANCH_CODE,B.BRANCH_NAME,B.BRANCH_CITY"+"FROMACCOUNTAJOINBRANCH B"+"ONA.BRANCH_CODE=B.BRANCH_CODE"+ "JOIN DEPOSITOR D ON A.ACCOUNT_NO=D.ACCOUNT_NOWHERE D.CUST_NO='"+cust_no3+"'";
						try{
						rs =
						stmt.executeQuery(sqlstr);rs.next();
						//
						System.out.println(rs.wasNull
						());if(!(rs.wasNull())){
						System.out.printf("%-10s|%-5s|%-9s|%-12s|%-20s|%10s\n","ACCOUNT_NO","TYPE","BRANCH","BRANCH_CODE","BRANCH_NAME","BRANCH_CITY");
						System.out.println("----------------------------------------------------------");
						System.out.printf("%-10s|%-5s|%-9d|%-12s|%-20s|%10s\n",rs.getString(1),rs.getString(2),rs.getLong(3),rs.getString(4),rs.getString(5),rs.getString(6));
						}
						}catch(Exception e){
						System.out.println("Invalid Customer Number or have'ntopened any account yet");
						}

			  break;
			
			case 6:
				System.out.println("Enterthecustomernumbertoshow details");
						String cust_no4 =br.readLine().toUpperCase();
						String displayStr="SELECTl.loan_no,l.amount,l.branch_code,b.branch_name,"+ " b.branch_city FROM LOAN l JOIN BRANCH b ON l.branch_code=b.branch_code"+"WHEREl.cust_no='"+cust_no4+"'";
						try{
						rs =
						stmt.executeQuery(displayStr);rs.next();
						if(!(rs.wasNull())){
						System.out.printf("%-7s|%-8s|%-7s|%-22s|%10s\n","LOAN_NO","AMOUNT","B_CODE","B_NAME","B_CITY");
						System.out.println("----------------------------------------------------------");
						System.out.printf("%-7s|%-8d|%-7s|%-22s|%10s\n",rs.getString(1),rs.getLong(2),rs.getString(3),rs.getString(4),rs.getString(5));
						}
						}
						catch(Exception e){
						System.out.println("Loandetailsnotfound");
						}

				break;

			case 7:
			//Deposit money
				try{
					System.out.println("Entertheaccountnumbertodeposit into");
					String acc_no =br.readLine().toUpperCase();
					System.out.println("Enter amount to be deposited: ");
					long amt=Long.parseLong(br.readLine());
					String depositStr="UPDATE ACCOUNTSET BALANCE=BALANCE+"+amt+"WHEREACCOUNT_NO='"+acc_no+"'";
					int row=stmt.executeUpdate(depositStr);
					if(row!=0){
					sqlstr="SELECT*FROMACCOUNTORDERBYACCOUNT_NO";
					rs=stmt.executeQuery(sqlstr);
					System.out.printf("%-10s|%-5s|%-9s|%6s\n","ACCOUNT_NO","TYPE","BALANCE","BRANCH_CODE");
					System.out.println("----------------------------------------------------------");
					while(rs.next())
					System.out.printf("%-10s|%-5s|%-9d|%6s\n",rs.getString(1),rs.getString(2),rs.getLong(3),rs.getString(4));
					}else{
					System.out.println("Accountnumberdoes'ntexist");
					}
					}catch(SQLException se)
					{se.printStackTrace()
					;
					}
				break;
			case 8:
				try{
					System.out.println("EnterthecustomerAccount Number");
					String Acc_no =br.readLine().toUpperCase();
					System.out.println("Enter the customer Amount");
					long Withdraw=Long.parseLong(br.readLine());
					rs=stmt.executeQuery("select balance FROM ACCOUNT WHEREACCOUNT_NO='"+Acc_no+"'");
					rs.next();
					long Balance =
					(rs.getLong(1));
					if(Withdraw>Balance)
						System.out.println("Insufficient Balance");
					else if (Withdraw<0)
						System.out.println("InvalidAmount");
					else
						Balance-=Withdraw;
					String withdrawStr="UPDATE ACCOUNT SETBALANCE="+Balance+"WHEREACCOUNT_NO='"+Acc_no+"'";
					int row=stmt.executeUpdate(withdrawStr);
					if(row!=0){
					sqlstr="SELECT*FROMACCOUNTORDERBYACCOUNT_NO";
					rs=stmt.executeQuery(sqlstr);
					System.out.printf("%-10s|%-5s|%-9s|%6s\n","ACCOUNT_NO","TYPE","BALANCE","BRANCH_CODE");
					System.out.println("----------------------------------------------------------");
					while(rs.next())
					System.out.printf("%-10s|%-5s|%-9d|%6s\n",rs.getString(1),rs.getString(2),rs.getLong(3),rs.getString(4));
					}else{
					System.out.println("Accountnumberdoes'ntexist");
					}
					}catch(SQLException se)
					{se.printStackTrace()
					;
					}

				break;
			default:
			System.out.println("Wrong choice");
			}
			
				
			}while(ch!=9);
			
				
			} //try closing bracket
			catch(Exception e)
			{ System.out.println(e);}

	//stmt.close();
	//con.close();
	}

}

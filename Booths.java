import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class Booths implements ActionListener
{
	int[] m,q;
	int[] ac = new int[8];
	int qneg=0;
	int[] shifted = new int[17];
	
	JFrame jf;
	Font f1,f2,f3,f4;
	JLabel l1,l2,ans,lt1,lt2,lt3;
	JTextField t1,t2,t3;
	JButton b;
	JLabel e0,e1,e2,e3,e4,e5,e6,e7,e8,e9;
	Booths()
	{
		jf = new JFrame("COA");
		jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		jf.setVisible(true);
		jf.setBounds(400,90,600,600);
		
		ImageIcon icon = new ImageIcon("coa_project/inf2.png");
		jf.setIconImage(icon.getImage());
		
		Container con = jf.getContentPane();
		con.setLayout(null);
		//con.setBackground(Color.CYAN);
		
		f1 = new Font("Lucida Calligraphy",Font.ITALIC,30);
		f2 = new Font("Lucida Calligraphy",Font.PLAIN,15);
		f3 = new Font("Lucida Handwriting",Font.PLAIN,20);
		f4 = new Font("Lucida Handwriting",Font.PLAIN,15);
		
	    l1 = new JLabel("<-Booth's Multiplication->");
		con.add(l1);
		l1.setBounds(90,0,450,30);		
		l1.setFont(f1);
		
		l2 = new JLabel("Enter numbers from -128 to less than +128");
		con.add(l2);
		l2.setBounds(125,40,450,20);		
		l2.setFont(f2);
		
		ans = new JLabel("Accumulator       (Q)Multiplier       Q-1");
		con.add(ans);
		ans.setBounds(120,180,450,20);
		ans.setFont(f3);
		
		lt1 = new JLabel("Enter 1'st number : ");
		con.add(lt1);
		lt1.setBounds(20,70,200,20);
		lt1.setFont(f2);
		lt2 = new JLabel("Enter 2'nd number : ");
		con.add(lt2);
		lt2.setBounds(290,70,200,20);
		lt2.setFont(f2);
		
		t1 = new JTextField();
		con.add(t1);
		t1.setBounds(180,70,100,20);
		t2 = new JTextField();
		con.add(t2);
		t2.setBounds(460,70,100,20);
		
		lt3 = new JLabel("Multiplication answer : ");
		con.add(lt3);
		lt3.setBounds(20,100,200,20);
		lt3.setFont(f2);
		t3 = new JTextField();
		con.add(t3);
		t3.setBounds(220,100,100,20);
		
		b = new JButton("<-Multiply->");
		con.add(b);
		b.setBounds(360,100,120,20);
		b.addActionListener(this);
		
		// label's for holding the execution
		
		e0 = new JLabel();
		con.add(e0);
		e0.setBounds(50,140,500,20);
		e0.setFont(f2);
		e1 = new JLabel();
		con.add(e1);
		e1.setBounds(10,210,550,25);
		e1.setFont(f3);
		e2 = new JLabel();
		con.add(e2);
		e2.setBounds(10,240,550,25);
		e2.setFont(f3);
		e3 = new JLabel();
		con.add(e3);
		e3.setBounds(10,270,550,25);
		e3.setFont(f3);
		e4 = new JLabel();
		con.add(e4);
		e4.setBounds(10,300,550,25);
		e4.setFont(f3);
		e5 = new JLabel();
		con.add(e5);
		e5.setBounds(10,330,550,25);
		e5.setFont(f3);
		e6 = new JLabel();
		con.add(e6);
		e6.setBounds(10,360,550,25);
		e6.setFont(f3);
		e7 = new JLabel();
		con.add(e7);
		e7.setBounds(10,390,550,25);
		e7.setFont(f3);
		e8 = new JLabel();
		con.add(e8);
		e8.setBounds(10,420,550,25);
		e8.setFont(f3);
		e9 = new JLabel("                       <-Execution is shown in upper block->");
		con.add(e9);
		e9.setBounds(10,470,560,25);
		e9.setFont(f4);
		
		con.add(new SwingCanvas());
	}
	
	public void actionPerformed(ActionEvent e)
	{
		int i;
		String str1 = "",str2 = "";
		int num1 = Integer.parseInt(t1.getText());
		int num2 = Integer.parseInt(t2.getText());
		int c = 0;
		if(e.getSource() == this.b)
			c = num1*num2;
		t3.setText(String.valueOf(c));
		
		if(num1 < 0)
		{
			m = binary(abs(num1));
			m = twosOfNumber(m);
		}
		else
			m = binary(abs(num1));
		if(num2 < 0)
		{
			q = binary(abs(num2));
			q = twosOfNumber(q);
		}
		else
			q = binary(abs(num2));
		
		System.out.print("binary of "+num1+" is ");
		for(i=0;i <= 7;i++)
			System.out.print(m[i]+" ");
	
		System.out.println();
		
		System.out.print("binary of "+num2+" is ");
		for(i=0;i <= 7;i++)
			System.out.print(m[i]+" ");
		System.out.println();
		for(i=0;i<8;i++)
		{
			str1 += " "+(m[i]);
			str2 += " "+(q[i]);
		}
		e0.setText("("+num1+")10"+" = ( "+str1+" )2"+"   and   "+"("+num2+")10"+" = ( "+str2+" )2");
		myBooths();
		// flushing the values after one operation
		for(i=0;i<8;i++)
		{
			m[i] = 0;
			q[i] = 0;
			ac[i] = 0;
		}
		qneg = 0;
		if(shifted[0] == 0)
		{
			if(num1 == -128 || num2 == -128)
				e9.setText("0 is in Cycle 8 but answer is still negative... see in answer block.");
			else
				e9.setText("0 in Cycle 8 shows the result is positive... see in answer block.");
		}
		else
			e9.setText("1 in Cycle 8 Shows the result is negative final value is 2's of this.");
	}
	
	void myBooths()
	{
		int j;
		String str1 = "",str2 = "";
		for(int i=0;i<8;i++)
		{
			System.out.println("Cycle "+(i+1));
			if((q[7] == 0 && qneg == 0) || (q[7] == 1 && qneg == 1))
			{
				rShift(ac,q,qneg);
				print();
			}
			else
			{
				if(q[7] == 0 && qneg == 1)
				{
					ac = addBinary(ac,m);
					rShift(ac,q,qneg);
					print();
				}
				else
				{
					if(q[7] == 1 && qneg == 0)
					{
						ac = addBinary(ac,twosOfNumber(m));
						rShift(ac,q,qneg);
						print();
						//m = twosOfNumber(m);
					}
				}
			}
			str1 = "";
			str2 = "";
			for(j=0;j<8;j++)
			{
				str1 += " "+ac[j];
				str2 += " "+q[j];
				console(str1,str2,i);
			}
		}
	}
	
	void console(String s1,String s2,int i)
	{
		int a = i+1;
		switch(i)
		{
			case 0 :
					e1.setText("Cycle "+a+"   "+s1+"    "+s2+"       "+qneg);
			case 1 :
					e2.setText("Cycle "+a+"   "+s1+"    "+s2+"       "+qneg);
			case 2 :
					e3.setText("Cycle "+a+"   "+s1+"    "+s2+"       "+qneg);
			case 3 :
					e4.setText("Cycle "+a+"   "+s1+"    "+s2+"       "+qneg);
			case 4 :
					e5.setText("Cycle "+a+"   "+s1+"    "+s2+"       "+qneg);
			case 5 :
					e6.setText("Cycle "+a+"   "+s1+"    "+s2+"       "+qneg);
			case 6 :
					e7.setText("Cycle "+a+"   "+s1+"    "+s2+"       "+qneg);
			case 7 :
					e8.setText("Cycle "+a+"   "+s1+"    "+s2+"       "+qneg);		
		}
	}
	
	void rShift(int acs[],int qs[],int qnegs)
	{
		int i,j=0;
		for(i=0;i<16;i++)
		{
			if(i<8)
				shifted[i] = acs[i];
			else
				if(i<16)
					shifted[i] = qs[j++];
		}
		shifted[i] = qnegs;
		for(i=16;i>0;i--)
		{
			shifted[i] = shifted[i-1];
		}
		// giving the values after shifting
		j=0;
		for(i=0;i<16;i++)
		{
			if(i<8)
				ac[i] = shifted[i];
			else
				if(i<16)
					q[j++] = shifted[i];
		}
		qneg = shifted[i];
	}
	
	int[] binary(int num)
	{
		int no[] = new int[8];
		for(int i=7;i >= 0;i--)
		{
			if(num == 1)
			{
				no[i] = num;
				break;
			}
			else
			{
				no[i] = num%2;
				num /= 2;
			}
		}
		return no;
	}
	
	int[] addBinary(int aca[],int ma[])
	{
		int carry = 0;
		int[] sum = new int[8];
		for(int i=7;i>=0;i--)
		{
			if(aca[i] == 0 && ma[i] == 0)
			{
				if(carry == 1)
				{
					sum[i] = 1;
					carry = 0;
				}
				else
					sum[i] = 0;
			}
			else
			{
				if((aca[i] == 1 && ma[i] == 0) || (aca[i] == 0 && ma[i] == 1))
				{
					if(carry == 1)
					{
						sum[i] = 0;
						carry = 1;
					}
					else
					{
						sum[i] = 1;
						carry = 0;
					}
				}
				else
				{
					if(aca[i] == 1 && ma[i] == 1)
					{
						if(carry == 1)
							sum[i] = 1;
						else
							sum[i] = 0;
						carry = 1;
					}
				}
			}
		}
		return sum;
	}
	
	int[] twosOfNumber(int no[])  // the function takes the starting address of the array so no copy of array is done!!
	{					          // same array values is changed.
		int temp[] = new int[8];
		for(int k=0;k<8;k++)
			temp[k] = no[k];    // since only pointers are passed no new space is taken to make the changes 
								// so this step is necessary to avoid the changes in original array...
		for(int i=7;i>=0;i--)
		{
			if(temp[i] == 1)
			{
				for(int j=i-1;j>=0;j--)
				{
					if(temp[j] == 0)
						temp[j] = 1;
					else
						temp[j] = 0;
				}
				break;
			}
		}
		return temp;
	}
	
	void print()
	{
		int j;
		for(j=0;j<8;j++)
			System.out.print(ac[j]+" ");
		System.out.print(" ");
		for(j=0;j<8;j++)
			System.out.print(q[j]+" ");
		System.out.print(" ");
		System.out.println(qneg);
	}
	
	int abs(int num)
	{
		if(num < 0)
			return -1*num;
		else 
			return num;
	}
	
	public static void main(String args[])
	{
		new Booths();
	}
}  
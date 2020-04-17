
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;
import java.util.Scanner;
import java.math.BigInteger;
public class RSA_12
{

	private BigInteger p=null,q=null,N,f,E=null,PT,CT;
	private BigInteger D=null;
	BigInteger ZERO=new BigInteger("0");
	BigInteger ONE=new BigInteger("1");
	BigInteger TWO=new BigInteger("2");
	
	Scanner sc=new Scanner(System.in);
	
	private int s[]=new int[100000];
	int r1=0;
	int count1=0;
	public void isprimee(int x)            /* To Find Prime Numbers Between 1 To F(n) */
	{
		int i =0;
		int num=0;
     	 	for (i = 1; i <= x; i++)         
       		{ 		  	  
          		int counter=0; 	  
          		for(num =i; num>=1; num--)
          		{
             		if(i%num==0)
	     			{
             			counter = counter + 1;
	     			}
          		}
          		if (counter ==2)
          		{
	    			s[r1]=i;
	    			r1++;
	    			count1++;
          		}	
       		}	
   	}
		
	public void Extended_Euclidean(long a,long b)
	{
		long x=0,y=1,x1=1,y1=0,temp,q,r;
		while(b!=0)
		{
			q=a/b;
			r=a%b;
			a=b;
			b=r;
			
			temp=x;
			x=x1-q*x;
			x1=temp;
			
			temp=y;
			y=y1-q*y;
			y1=temp;
			
		
		}
		System.out.println("\nX:="+x1+"\nY:="+y1);
		long temp1;
		if(y1<0)
		{
			temp1=f.longValue()+y1;
			D=BigInteger.valueOf(temp1);
		}
		else
		{
			D=BigInteger.valueOf(y1);
		}
		}
	public void E_select(int fn)              /* To Calculate Value Of E */
	{
		boolean temp=false;
		Random ra=new Random();
		int random;
		while(!temp)
		{
			random=ra.nextInt(fn);
			if(isprime(random))
			{
				if(fn%random!=0)
				{
					E=BigInteger.valueOf(random);
					temp=true;
				}
			}
		}
	}

	int n1=2;
	int r;
	int count=0;
	int a[]=new int[10];
	int v=2;
	public void Factor_Phi(int f)                        /* To Find factor of Phi(n) i.e F(n)*/
	{
		if((f%n1)==0)
		{
			r=f/n1;
			a[++count]=n1;
			v++;
			Factor_Phi(r);
		}
		else if(r==1)
		{
			
		}
		else if(f%n1!=0)
		{
			n1++;
			r=f/n1;
			a[++count]=n1;
			a[++count]=r;
		}
		else if(f%v==0)
		{
			a[++count]=v;
		}
		else
		{
		}
	}
	public boolean isprime(int x)           /* Check Whether Enter Number is Prime Or Not */
	{
		int flag=0;
		if(x==0 || x==1)
			return false;
		for(int i=2;i<=x/2;i++)
		{
			if(x%i==0)
			{
				flag=1;
				break;
			}
		}
		if(flag==0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public void accept()
	{
		do
		{
			System.out.println("Enter the First Number : ");
			p=sc.nextBigInteger();
		}while(!isprime(p.intValue()));
		System.out.println("!!!The First Prime Number is : !!!");
		System.out.println(p);
		
		do
		{
			System.out.println("Enter the Second Number : ");
			q=sc.nextBigInteger();
		}while(!isprime(q.intValue()));
		System.out.println("!!!The second Prime Number is : !!!");
		System.out.println(q);
		
		N=p.multiply(q);
		System.out.println("N = "+N);
		
		f=p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
		System.out.println("F(n) = "+f);
		
		Factor_Phi(f.intValue());
		
		for(int j=1;j<=count;j++)
		{
			System.out.println(a[j]);
		}
		
		isprimee(f.intValue());
		
		System.out.println("Prime numbers from 1 to "+f+" are :");
		for(int i=0;i<count1;i++)
		{
			System.out.print("\t"+s[i]);
		}
		
		E_select(f.intValue());                            /* Select E Function is called*/
		System.out.println();
		System.out.println("The value of E is : ");
		System.out.println(E);
		
		
		Extended_Euclidean(f.longValue(),E.longValue());   /* Extended Euclidean Algorithm For Selecting D */
		System.out.println();
		System.out.println("The value of D is : ");
		System.out.println(D);
		
		System.out.println("Enter message M: ");				
		PT = sc.nextBigInteger();
		
		CT = (PT.pow(E.intValue())).mod(N);					
		System.out.println("Encrypted cipher text CT: "+CT);
		
		DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		Calendar calobj = Calendar.getInstance();
		String starttime=df.format(calobj.getTime());
		System.out.println(starttime);
		
		PT = (CT.pow(D.intValue())).mod(N);
		System.out.println("Original message M: "+PT);
		
		DateFormat df1 = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		Calendar calobj1 = Calendar.getInstance();
		String endtime=df1.format(calobj1.getTime());
		System.out.println(endtime);
		
	}
	public static void main(String[] args) {
		RSA_12 r=new RSA_12();
		r.accept();
		

	}

}

/*			OUTPUT

Enter the First Number : 
11
!!!The First Prime Number is : !!!
11
Enter the Second Number : 
17
!!!The second Prime Number is : !!!
17
N = 187
F(n) = 160

The value of E is : 
97

The value of D is : 
33
Enter message M: 
3
Encrypted cipher text CT: 20
26/10/18 07:53:31
Original message M: 3
26/10/18 07:53:31
*/

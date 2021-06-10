package com.company;

import java.math.BigInteger;


public class Main {

   

    public static int[] continuous_fraction(BigInteger N, BigInteger e) {   //this function find the continuous fraction of the numbers given
        int[] a= new int[1000];
        a[0]=0;
        BigInteger one=new BigInteger("1");
        BigInteger temp=new BigInteger("1");
        int i=1;
        while(N.divide(e).compareTo(BigInteger.valueOf(0)) == 1 ){
            temp=N;
            N=e;
            e=temp;
            BigInteger x=e.divide(N);
            a[i]= (int) Math.floor(x.doubleValue());
            e=e.subtract(N.multiply(BigInteger.valueOf(a[i])));
            i++;
            if (e.compareTo(BigInteger.valueOf(0))==0)
                break;
        }
        int[] a_=new int[i];
        if (i >= 0) System.arraycopy(a, 0, a_, 0, i);
        return a_;

    }

    public static int[][] k_d(int[] a){          //this function finds the convergents of the pinax a -the continuous fraction
        int[][] kd=new int[a.length][2];
        kd[1][0]=1;
        kd[1][1]=a[1];

        for (int i=2;i<a.length;i++){
            int ar=1, par=1;
            int temp1=a[i];
            for(int j=i;j>1;j--){
                par=a[j-1]*temp1+ar;
                ar=temp1;
                temp1=par;
            }
            kd[i][0]=ar;
            kd[i][1]=par;
        }

        return kd;

    }

    public static BigInteger f(BigInteger e, int k, int d){              //in this part we calculate the f function
        return (e.multiply(BigInteger.valueOf(d)).subtract(BigInteger.valueOf(1))).divide(BigInteger.valueOf(k));
    }

    public static boolean eq(BigInteger N, BigInteger f){   // this functions finds the solution to the equation we need to solve in order to get the p, q

        BigInteger x1=new BigInteger("0");
        BigInteger x2=new BigInteger("0");
        BigInteger D=new BigInteger("0");
        BigInteger temp=new BigInteger("0");
        temp=N.negate().add(f).subtract((BigInteger.ONE));
        D=temp.pow(2).subtract(N.multiply(BigInteger.valueOf(4)));
        if (D.compareTo(BigInteger.valueOf(0))<0)
            return false;
        if (D.compareTo(BigInteger.valueOf(0))==0)
            if(temp.compareTo(N)==0)
                return true;


        x1=temp.subtract(D.sqrt());
        x1=x1.divide(BigInteger.valueOf(2));
        x2=temp.add(D.sqrt());
        x2=x2.divide(BigInteger.valueOf(2));
        return x1.multiply(x2).compareTo(N) == 0;

    }

    public static boolean check_effectiveness(BigInteger N, int d){     //in this final part, we check whether the number "d" is efficient enough or not
        return N.pow(1 / 4).divide(BigInteger.valueOf(3)).compareTo(BigInteger.valueOf(d)) < 0;
    }

    public static void main(String[] args) {
	BigInteger N=new BigInteger("194749497518847283");
	BigInteger e=new BigInteger("50736902528669041");



	int[] a;
	a=continuous_fraction(N,e);


	int[][] kd;
	kd=k_d(a);

	int d=0;
	System.out.println();
	for (int i=1;i<a.length;i++){
	    BigInteger f=new BigInteger("0");
	    f=f(e,kd[i][0],kd[i][1]);
	    if (eq(N,f)) {
            d = kd[i][1];
            System.out.println(d);
            break;
        }
    }
	if (check_effectiveness(N,d)){
	    System.out.println("All good:)");
    }else
        System.out.println("Not the best bro:(");



    }
}

package MaximumPalindrome;

import java.io.*;
import java.util.*;
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        mod=(int)1e9+7;
        int max=(int)(1e5+5);

        int [] fac=new int [max];
        int [] inv=new int [max];

        fac[0]=1;
        for(int i=1;i<max;i++)
            fac[i]=(int)(1L*fac[i-1]*i%mod);
        for(int i=0;i<max;i++)
            inv[i]=(int)inv(fac[i]);

        char [] s=br.readLine().toCharArray();
        int n=s.length;
        int q=Integer.parseInt(br.readLine());

        int [][] dp=new int [n][26];
        dp[0][s[0]-'a']++;
        for(int i=1;i<n;i++){
            for(int j=0;j<26;j++)
                dp[i][j]+=dp[i-1][j];
            dp[i][s[i]-'a']++;
        }
        while(q-->0){
            String query[] = br.readLine().split(" ");
            int l=Integer.parseInt(query[0])-1;
            int r=Integer.parseInt(query[1])-1;

            int odd=0;
            int even=0;

            int [] f=new int [26];
            long res=1;

            for(int i=0;i<26;i++){
                f[i]=dp[r][i];
                if(l>0)
                    f[i]-=dp[l-1][i];
                if(f[i]%2==1){
                    odd++;
                    f[i]--;
                }
                f[i]/=2;
                res=(res*inv[f[i]])%mod;
                even+=f[i];
            }
            res=(res*fac[even])%mod;
            if(odd>0)
                res*=odd;
            res%=mod;
            System.out.println(res);
        }
    }
    static int mod;
    public static long inv(long x){
        long r,y;
        for(r=1,y=mod-2;y!=0;x=x*x%mod,y>>=1)
            if((y&1)==1)
                r=r*x%mod;
        return r;
    }

}
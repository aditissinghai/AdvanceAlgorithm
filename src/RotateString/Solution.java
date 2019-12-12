package RotateString;

class Solution {
    public boolean rotateString(String A, String B) {

        int N = A.length();
        int M = B.length();

        if(N != M) {
            return false;
        }else if(N==0 && M==0) {
            return true;
        } else if(N == 0) {
            return false;
        }

        A = A+A;
        // Compute failure table which maintains size of suffix which is same as prefix

        int[] failure = new int[M];
        int index = 0;

        for(int i = 1; i < M;) {

            if(B.charAt(i) == B.charAt(index)) {
                failure[i] = index+1;
                index++;
                i++;
            } else {
                if(index != 0) {
                    index = failure[index-1];
                }else {
                    failure[i] = 0;
                    i++;
                }
            }
        }

        int i = 0;
        int j = 0;

        while(i < A.length() && j < M) {
            if(A.charAt(i) == B.charAt(j)) {
                i++;
                j++;
            } else {
                if(j!=0) {
                    j = failure[j-1];
                }else {
                    i++;
                }
            }
        }

        if(j == M) {
            return true;
        }

        return false;
    }
}
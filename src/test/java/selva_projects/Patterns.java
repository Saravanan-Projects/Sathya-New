package selva_projects;

public class Patterns {
    static void pattern1(){
        int n = 6;
        for(int i=1;i<n;i++){
            for(int j=1;j<=i;j++){
                System.out.print(j);
            }
            System.out.println();
        }
    }
    static void patternSample(){
        int n = 6;
        for(int i=1;i<n;i++){
            for(int j=1;j<=n-i;j++){
                System.out.print(j);
            }
            System.out.println();
        }
    }
    static void pattern3(){
        int n = 5,j;
        boolean res = true;
        for(int i=1;i<=n*2;i++){
            for(j=1;res;){
                System.out.print(j);
                if (i<=n) {
                    j++;
                    res = j<=i;
                }
                else {
                    j--;
                    res = j<=n-i;
                }
            }
            System.out.println();
        }
    }
    public static void main(String... a){
//        pattern1();
        pattern3();
    }
}

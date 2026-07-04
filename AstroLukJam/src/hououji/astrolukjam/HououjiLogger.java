package hououji.astrolukjam;

public class HououjiLogger {

    public HououjiLogger(String TAG) {
        this.TAG = TAG ;
    }

    String TAG = "" ;
    public void debug(String s) {

//        Log.d(TAG,s ) ;
        System.out.println("DEBUG " + TAG + ":" +s) ;
    }

    public void warn(String s) {
//        Log.w(TAG,s);
        System.out.println("DEBUG " + TAG + ":" +s) ;
    }
}

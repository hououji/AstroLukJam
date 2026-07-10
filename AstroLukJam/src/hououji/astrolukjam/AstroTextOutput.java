package hououji.astrolukjam;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class AstroTextOutput extends LukJamGraph {
	AstroTextCastle o [] = new AstroTextCastle[12] ;
//	List<String> output = new ArrayList<String>() ;
	String output[][] = new String[33][33]; // [y][x], [0][0] is Left-top
	
	public void printH(int y, int x, String s) {
		int col = 0;
		for(int i=0; i<s.length(); i++) {
			char ch = s.charAt(i)  ;
			if(col % 2 == 0) {
				output[y][x+col/2] = "" ;
			}
			output[y][x+col/2] += (ch + "");
			if(  ch >= 0 && ch <= 127  ) {
				col = col + 1 ;
			}else {
				col = col + 2 ;
			}
		}
	}

	public void print(int y, int x, String[][] s) {
		for(int i=0; i<s.length ;i++) { // y
			for(int j=0; j<s[i].length; j++) { //x
				output[y+i][x+j] = s[i][j] ;
			}
		}
	}
	
//	public void print (int y, int x, String s) {
//		for(int i=0; i<s.length() ;i ++) {
//			output[y][x+i] = s.charAt(i) + "" ;
//		}
//	}
	
	public void printClean(int y, int x, int hieght, int width) {
		for(int i=0;i<hieght; i++) {
			for(int j=0;j<width; j++) {
				output[y+i][x+j] = "\u3000" ;
			}
		}
	}
	
	public void output() {
		for(int i = 0 ; i<o.length; i++) {
			o[i] = new AstroTextCastle() ;
			o[i].sky = skys[cycleGround2Sky[skyPlane[i]]] + 
					gods[skyPlanelead[(skyPlane[i]-skyPlane[0]+12)%12]]
							+ grounds[skyPlane[i]] ;
			if(fourChapter[0][1] == skyPlane[i]) {
				o[i].fourChapterActive = o[i].fourChapterActive + "干上" ;
			}
			if(fourChapter[2][1] == skyPlane[i]) {
				o[i].fourChapterActive = o[i].fourChapterActive + "支上" ;
			}
			if(fourChapter[1][1] == skyPlane[i]) {
				o[i].fourChpaterPassive = o[i].fourChpaterPassive + "干陰" ;
			}
			if(fourChapter[3][1] == skyPlane[i]) {
				o[i].fourChpaterPassive = o[i].fourChpaterPassive + "支陰" ;
			}
			if(threePass[0] == skyPlane[i]) {
				o[i].threePass = o[i].threePass + "初" ;
			}
			if(threePass[1] == skyPlane[i]) {
				o[i].threePass = o[i].threePass + "中" ;
			}
			if(threePass[2] == skyPlane[i]) {
				o[i].threePass = o[i].threePass + "末" ;
			}
			if(!"".equals(o[i].threePass)) {
				o[i].threePass = o[i].threePass + "傳" ;
			}
			if(monthlead == skyPlane[i]) {
				o[i].lead = "月將" ; 
			}
			
			for(int j=0; j<this.resultSevenBodiesCastle.length; j++ ) {
				if(this.resultSevenBodiesCastle[j] == skyPlane[i]) {
					o[i].sevenBodies.add(makeSevenBodyStr(this.resultSevenBodies[j])) ;
				}
					
			}
			// TODO status
			
			
			
			o[i].make() ;
		}
		
		
		for(int i=0; i<output.length; i ++) {
			for(int j=0; j<output[i].length ; j++) {
				output[j][i] = "＃" ;
			}
		}
		
		print(25,17, o[0].output) ;
		print(25,9, o[1].output) ;
		print(25,1, o[2].output) ;
		print(17,1, o[3].output) ;
		print(9,1, o[4].output) ;
		print(1,1, o[5].output) ;
		print(1,9, o[6].output) ;
		print(1,17, o[7].output) ;
		print(1,25, o[8].output) ;
		print(9,25, o[9].output) ;
		print(17,25, o[10].output) ;
		print(25,25, o[11].output) ;

		printClean(9,9,15,15) ;
		printH(10,11, toFullWidthNumber(new SimpleDateFormat("yyyy-MM-dd").format(this.date)) );
		printH(11,11, toFullWidthNumber(new SimpleDateFormat("HH:mm").format(this.date))) ;
		
		//四柱
		String timeStr = "" ;
		for(int i=0; i<eight.length; i++) {
			timeStr += skys[get10(eight[i])] + grounds[get12(eight[i])] ;
			timeStr += "\u3000" ;
		}
		printH(13,11, toFullWidthNumber(timeStr)) ;
		 
		print(15, 11, this.getResult()) ;
	}
	
	private String makeSevenBodyStr(String org) {
		//sample 月20戌32, is 20 degree, and 32 is minutes 
		return toFullWidthNumber(org.substring(0,3)) ;
	}
	public static String toFullWidthNumber(String input) {
        if (input == null) return null;
        
        char[] chars = input.toCharArray();
        String half = ":-" ;
        String full = "\uFF1a—" ;
        for (int i = 0; i < chars.length; i++) {
            // 半形數字的範圍在 '0' (48) 到 '9' (57)
            if (chars[i] >= '0' && chars[i] <= '9') {
                chars[i] = (char) (chars[i] + 65248);
            }
            int idx = half.indexOf(chars[i]) ;
            if(idx >=0) {
            	chars[i] = full.charAt(idx) ;
            }
        }
        return new String(chars);
    }
	
	public void print() {
		for(int i=0; i<output.length; i ++) { // y
			for(int j=0; j<output[i].length ; j++) { // x
				System.out.print(output[i][j]);
			}
			System.out.println();
		}
	}
}
class AstroTextCastle{
	public AstroTextCastle() {
		for(int i=0; i<output.length; i++) {
			output[i] = new String[7] ;
			for(int j=0; j < output[i].length; j++) {
				output[i][j] = "\u3000" ;
			}
		}
	}
	
	String[][] output = new String[7][7] ; // [y][x], [0][0] is Left-top
	String sky = ""; // 干, 貴人, 天將 eg 乙貴丑
	String fourChapterActive = "" ; // 干上/支上, 第1,3課
	String fourChpaterPassive = "" ; // 干陰/支陰
	String threePass = "" ; // eg 初中末 
	String status = "" ; // eg 賊/尅
	String lead = "" ; // EG 月將
	List<String> sevenBodies= new ArrayList<String>() ;
	
//	public String exportLine(int y) {
//		String line = "" ;
//		for(int x=0; x<output[y].length; x++) {
//			line = line + output[y][x] ;
//		}
//		return line;
//	}
	
	protected void make() {
		print(0,6,sky) ;
		print(0,5,fourChapterActive) ;
		print(0,4,fourChpaterPassive) ;
		print(0,3,threePass) ;
		print(0,2,status) ;
//		print(0,1,lead) ;
		for(int i=0; i<this.sevenBodies.size(); i++) {
			print(4, 6-i, this.sevenBodies.get(i)) ;
		}
		
	}
	
	private void print( int y, int x, String s) {
		int i=0;
		for(char c : s.toCharArray()) {
			output[y+i][x] = c + "" ;
			i++ ;
		}
	}
}
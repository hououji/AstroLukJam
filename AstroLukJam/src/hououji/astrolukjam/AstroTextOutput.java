package hououji.astrolukjam;

import java.util.ArrayList;
import java.util.List;

public class AstroTextOutput extends LukJamGraph {
	AstroTextCastle o [] = new AstroTextCastle[12] ;
	List<String> output = new ArrayList<String>() ;
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
			// TODO status
			
			
			
			o[i].make() ;
		}
		
		String line = "＃＃＃＃＃＃＃＃＃＃＃＃＃＃＃＃＃＃＃＃＃＃＃＃＃＃＃＃＃＃＃" ;
		String emptyLine = "\u3000\u3000\u3000\u3000\u3000\u3000\u3000" ;
		String line2 = "＃"+emptyLine+"＃"+emptyLine+"＃" ;
		output.add(line) ;
		for(int y=0;y<o[0].output[0].length; y++) {
			output.add(o[5].exportLine(y) + "＃" + o[6].exportLine(y) + "＃" + o[7].exportLine(y) + "＃" + o[8].exportLine(y)) ;
		}
		output.add(line) ;
		for(int y=0;y<o[0].output[0].length; y++) {
			output.add(o[4].exportLine(y) + "＃" +emptyLine + "\u3000" + emptyLine + "＃" + o[9].exportLine(y)) ;
		}
		output.add("＃＃＃＃＃＃＃＃" + emptyLine + emptyLine + "\u3000" + "＃＃＃＃＃＃＃＃") ;
		for(int y=0;y<o[0].output[0].length; y++) {
			output.add(o[3].exportLine(y) + "＃" +emptyLine + "\u3000" + emptyLine + "＃" + o[10].exportLine(y)) ;
		}
		output.add(line) ;
		for(int y=0;y<o[0].output[0].length; y++) {
			output.add(o[2].exportLine(y) + "＃" + o[1].exportLine(y) + "＃" + o[0].exportLine(y) + "＃" + o[11].exportLine(y)) ;
		}
		output.add(line) ;
	}
	public void print() {
		for (String s : output) {
			System.out.println(s);
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
	
	public String exportLine(int y) {
		String line = "" ;
		for(int x=0; x<output[y].length; x++) {
			line = line + output[y][x] ;
		}
		return line;
	}
	
	protected void make() {
		print(0,6,sky) ;
		print(0,5,fourChapterActive) ;
		print(0,4,fourChpaterPassive) ;
		print(0,3,threePass) ;
		print(0,2,status) ;
		print(0,1,lead) ;
		
	}
	
	private void print( int y, int x, String s) {
		int i=0;
		for(char c : s.toCharArray()) {
			output[y+i][x] = c + "" ;
			i++ ;
		}
	}
}
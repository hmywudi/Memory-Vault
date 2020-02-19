package sudoku;

import java.util.*;

public class Sudoku {

	// this is the Sudoku table
	private static int[][] table = {{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0}};
	private static Scanner scanner = new Scanner(System.in);
	private static final int[] ONETONINE = {1,2,3,4,5,6,7,8,9};
	
	public static void main(String[] args) {
	
		//1. get the sudoku table and put it into 2D array table[][]
		String message = "Please enter the Sudoku you want to solve: ";
		System.out.println(message);
		String sudoku = scanner.nextLine();
		String[] sudokuString = sudoku.split(" ");
		int[] realSudoku = new int[81];
		
		for(int a = 0;a<sudokuString.length;a++){
			
			realSudoku[a]= Integer.valueOf(sudokuString[a]);
		//	System.out.print(realSudoku[a]);
		}
		
	
		getTable(realSudoku);
	
		/* Rule of Sudoku
		 1. all index with 123, 456, 789, 147, 258, 369 should be in order 1-9
		 2. all index with 123456789 should be in order 1-9
		 How to check if the order is right?
		 1. extract all number of the group.
		 2. store it in a new array with 9 elements, cannot be duplicated every time when put a number in.
		 How to put a number in?
		 1. check if the blank is 0, if not, skip.
		 2. if the blank is 0, fill the blank in order of 1-9.
		 3. check all associated blanks to make sure the order is right
		 */
		
		boolean checkResult = false;
		ArrayList<Integer> validRange = new ArrayList<Integer>();
		
		for (int i = 0;i<9;i++) {
			for(int j = 0;j<9;j++) {
				
				if(table[i][j] ==0) {
				validRange = getValidRange(i,j);
				
					if  (validRange.size() != 0 && validRange.size() != 1) {
						table[i][j]=validRange.get((int) (Math.random()*validRange.size()));
					}else if (validRange.size() ==1) {
						table[i][j] = validRange.get(0);
						realSudoku[(i*9)+j] = table[i][j];
					
					}else{
					i=0;j=0;
					
					getTable(realSudoku);
					}
				}
		}
		}
		for(int[] la: table) {
			for(int lala : la) {
			System.out.print( lala);
			}
			System.out.println();
		}
		
		
	}

	// this method is to check if the order is right in an 1D 9 elements array
	public static boolean check(int[] array){
		for(int c = 0; c<9;c++) {
			for(int i = 0; i<9;i++) {
				if(array[c]== array[i] && c != i && array[i] != 0) {
					return false;
				}else return true;

			}
		}
		
		return true;
		
	}

	public static  ArrayList<Integer> getValidRange(int a, int b) {
		ArrayList<Integer> validRange = new ArrayList<Integer>();
		int[] checkArray = new int[9];
		for (int i : ONETONINE)	{
			validRange.add(i);
		}
		
		for(int i =	0; i<9;i++){		
			for(int c = 1; c<=9;c++) {
				if (table[a][i] == c) {
					validRange.remove(Integer.valueOf(c));
				}	
			}
		}
		//012,345,678,036,147,258
			for (int i = 0;i<3;i++) {
				for(int j = 0;j<3;j++) {
					validRange.remove(Integer.valueOf(table[a-a%3+i][b-b%3 + j]));
					validRange.remove(Integer.valueOf(table[a%3+(3*i)][b%3 + (3*j)]));
				}
			}
	/*	for(int la: validRange) {
			System.out.print(la);
		}*/
		return validRange;
	}

	public static int[][] getTable(int[] input){
		
		
		int x =0;
	
		for(int r = 0;r<9;r++){
			
			for(int c = 0; c<9;c++) {
				table[r][c]= input[x];
				
				x++;				
			
			}
		}
		

		return table; 
	}
}

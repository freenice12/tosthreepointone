package springbook.learningtest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Calculator {
	public Integer calcSum(String path) throws IOException {
//		BufferedReaderCallback sumCallBack = new BufferedReaderCallback() {
//
//			@Override
//			public Integer doSomethingWithReader(BufferedReader br) throws IOException {
//				Integer sum = 0;
//				String line = null;
//				while ((line = br.readLine()) != null) {
//					sum += Integer.valueOf(line);
//				}
//				return sum;
//			}
//		};
//
//		return fileReadTemplate(path, sumCallBack);
		// BufferedReader br = null;
		// try {
		// br =new BufferedReader(new FileReader(path));
		// Integer sum = 0;
		// String line = null;
		// while ((line = br.readLine()) != null) {
		// sum += Integer.valueOf(line);
		// }
		// br.close();
		// return sum;
		// } catch (IOException e) {
		// System.out.println(e);
		// throw e;
		// } finally {
		// if (br != null) { try {br.close();} catch (IOException e)
		// {e.getMessage();} }
		// }
		
		LineCallback<Integer> sumCallback = new LineCallback<Integer>() {
			
			@Override
			public Integer doSomethingWithLine(String line, Integer value) {
				return value + Integer.valueOf(line);
			}
		};
		
		return lineReadTemplate(path, sumCallback, 0);

	}

	public Integer calcMultiply(String path) throws IOException {
//		BufferedReaderCallback multiplyCallBack = new BufferedReaderCallback() {
//
//			@Override
//			public Integer doSomethingWithReader(BufferedReader br) throws IOException {
//				Integer multiply = 1;
//				String line = null;
//				while ((line = br.readLine()) != null) {
//					multiply *= Integer.valueOf(line);
//				}
//				return multiply;
//			}
//		};
//
//		return fileReadTemplate(path, multiplyCallBack);
		LineCallback<Integer> multiplyCallback = new LineCallback<Integer>() {
			
			@Override
			public Integer doSomethingWithLine(String line, Integer value) {
				return value * Integer.valueOf(line);
			}
		};
		
		return lineReadTemplate(path, multiplyCallback, 1);

	}

	public Integer fileReadTemplate(String path, BufferedReaderCallback callback) throws IOException {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(path));
			return callback.doSomethingWithReader(br);
		} catch (IOException e) {
			System.out.println(e);
			throw e;
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.getMessage();
				}
			}
		}
	}

	public <T> T lineReadTemplate(String path, LineCallback<T> callback, T initVal) throws IOException {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(path));
			T res = initVal;
			String line = null;
			while ((line = br.readLine()) != null) {
				res = callback.doSomethingWithLine(line, res);
			}
			return res;
		} catch (IOException e) {
			System.out.println(e);
			throw e;
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.getMessage();
				}
			}
		}
	}
	
	public String concatenate(String path) throws IOException {
		LineCallback<String> concatCallback = new LineCallback<String>() {
			
			@Override
			public String doSomethingWithLine(String line, String value) {
				return value + line;
			}
		};
		
		return lineReadTemplate(path, concatCallback, "");
		
	}

}

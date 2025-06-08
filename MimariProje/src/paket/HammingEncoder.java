package paket;

class HammingEncoder {
	 
	public static String encode(String dataBits) {
	      
			int dataLength = dataBits.length();
	        int r = 0;
	    
	        while (Math.pow(2, r) < (dataLength + r + 1)) {
	            r++;
	        }

	        int totalLength = dataLength + r + 1; 
	        char[] hammingCode = new char[totalLength + 1]; 

	        for (int i = 1; i <= totalLength; i++) {
	            hammingCode[i] = '0';
	        }

	        int j = 0;
	        
	        for (int i = 1; i <= totalLength; i++) {
	            if (!isPowerOfTwo(i) && i != 1) {
	                if (j < dataBits.length()) {
	                    hammingCode[i] = dataBits.charAt(j++);
	                }
	            }
	        }

	        for (int i = 0; i < r; i++) {
	            int parityPos = (int) Math.pow(2, i);
	            int parity = 0;
	            for (int k = 1; k <= totalLength; k++) {
	                if (((k >> i) & 1) == 1 && k != parityPos) {
	                    if (hammingCode[k] == '1') parity ^= 1;
	                }
	            }
	            hammingCode[parityPos] = (char) (parity + '0');
	        }

	        int overall = 0;
	        for (int i = 2; i <= totalLength; i++) {
	            if (hammingCode[i] == '1') overall ^= 1;
	        }
	        hammingCode[1] = (char) (overall + '0');

	        return new String(hammingCode, 1, totalLength);
	    }

	
	    public static String detectAndCorrect(String code) {
	        int n = code.length();
	        int r = 0;
	        while ((int) Math.pow(2, r) < n) r++;

	        int syndrome = 0;

	        for (int i = 0; i < r; i++) {
	            
	        	int parityPos = (int) Math.pow(2, i);
	            int parity = 0;
	            
	            for (int k = 1; k <= n; k++) {
	                
	            	if (((k >> i) & 1) == 1) {
	                    if (code.charAt(k - 1) == '1') 
	                    	parity ^= 1;
	                }
	            }
	            	
	            	if (parity == 1) { 
	            		syndrome += parityPos;
	            	}
	        }

	        StringBuilder sb = new StringBuilder(code);

	        if (syndrome == 0) {
	            return "Hata tespit edilmedi.\nVeri dogru.\nKod:\n" + code;
	        } else if (syndrome <= n) {
	            sb.setCharAt(syndrome - 1, (sb.charAt(syndrome - 1) == '0') ? '1' : '0');
	            return "Hata tespit edildi! Pozisyon: " + syndrome + "\nDuzeltilmis Kod:\n" + sb.toString();
	        } else {
	            return "Cift hata veya gecersiz hata bulundu ve duzeltilemedi.";
	        }
	    }

	    
	    private static boolean isPowerOfTwo(int x) {
	        return (x & (x - 1)) == 0;
	    }
}


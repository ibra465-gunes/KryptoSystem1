
public class KryptoSystem1 {

    private static char[] alfabet = {'A', 'B', 'C', 'Ç', 
        'D', 'E', 'F', 'G', 'Ğ', 'H', 'I', 'İ', 'J',             //Kullanılan alfabe oluşturulmuştur. 
        'K', 'L', 'M', 'N', 'O', 'Ö', 'P', 'R', 'S', 
        'Ş', 'T', 'U', 'Ü', 'V', 'Y', 'Z'};

    public String enCrypt(String message, String key) {
        message = message.toUpperCase();               //Girilen metnin ve anahtarın karakterleri büyük harfe dönüştürülüyor.
        key = key.toUpperCase();                       
        int[] num = new int[message.length()];         //Metin boyutunda metin ve anahtar için iki tane int tipinde dizi oluşturuluyor.
        int[] num1 = new int[message.length()];
        StringBuilder reMessage = new StringBuilder();
        int total = 0;
        if (key.length() < message.length()) {
            StringBuilder reKey = new StringBuilder();
            while (reKey.length() < message.length()) { //Anahtarın karakterlerinin metin uzunluğuna gelecek kadar tekrarlanması işlemi 
                reKey.append(key);
            }
            key = reKey.substring(0, message.length());
        }
        else if(key.length()>message.length()){
            key = key.substring(0,message.length()); //Anahtar metin uzunluğuna kısaltılması işlemi
        }
        for (int i = 0; i < message.length(); i++) {
            for (int j = 0; j < alfabet.length; j++) {
                if (alfabet[j] == message.charAt(i)) {
                    num[i] = j + 1;
                }
            }                                                       //Metin ve anahtarda bulunan sayısal karşılığı dizilere yerleştiriliyor
        }
        for (int i = 0; i < key.length(); i++) {
            for (int j = 0; j < alfabet.length; j++) {
                if (alfabet[j] == key.charAt(i)) {
                    num1[i] = j + 1;
                }
            }
        }
        for (int i = 0; i < message.length(); i++) {

            if (i == message.length() - 1) {
                total = num[i] * (num1[i] + num1[0]);
            } else {                                                //Karakter karakter şifreleme işlemi
                total = num[i] * (num1[i] + num1[i + 1]);
            }
            int index = total % alfabet.length;
            if (index == 0) {
                index = num[i]+5;
                index = index%alfabet.length;
            }
            reMessage.append(alfabet[index - 1]);                   //Şifreli metinnin karakterlerinin eklenmesi
        }
        message = reMessage.substring(0, message.length());
        return message;
    }

    public String deCrypt(String message, String key) {
        message = message.toUpperCase();
        key = key.toUpperCase();
        int[] num = new int[message.length()];
        int[] num1 = new int[message.length()];
        StringBuilder reMessage = new StringBuilder();
        int total = 0;
        if (key.length() < message.length()) {
            StringBuilder reKey = new StringBuilder();
            while (reKey.length() < message.length()) {
                reKey.append(key);
            }
            key = reKey.substring(0, message.length());
        }
        else if(key.length()>message.length()){
            key = key.substring(0,message.length());
        }
        for (int i = 0; i < message.length(); i++) {
            for (int j = 0; j < alfabet.length; j++) {
                if (alfabet[j] == message.charAt(i)) {
                    num[i] = j + 1;
                }
            }
        }
        for (int i = 0; i < key.length(); i++) {
            for (int j = 0; j < alfabet.length; j++) {
                if (alfabet[j] == key.charAt(i)) {
                    num1[i] = j + 1;
                }
            }
        }
        for (int i = 0; i < message.length(); i++) {                //Şifreli metinden düz metin elde etme işlemi
            int index = 0;
            if (i == message.length() - 1) {
                if ((num1[i] + num1[0])%alfabet.length == 0) {
                    index = num[i]-5;
                    if(index<0){
                        index = index + alfabet.length;
                    }
                } else {
                    int m = alfabet.length;
                    int a = num1[i] + num1[0];
                    int m0 = m;
                    int y = 0, x = 1;
                    while (a > 1) {
                        int q = a / m;
                        int t = m;
                        m = a % m;
                        a = t;
                        t = y;
                        y = x - q * y;
                        x = t;
                    }
                    if (x < 0) {
                        x += m0;
                    }
                    total = num[i] * (x);
                    index = total % alfabet.length;
                }
            } else {
                if ((num1[i] + num1[i + 1])%alfabet.length == 0) {
                    index = num[i]-5;
                    if(index<0){
                        index = index + alfabet.length;
                    }
                } else {
                    int m = alfabet.length;
                    int a = num1[i] + num1[i + 1];
                    int m0 = m;
                    int y = 0, x = 1;
                    while (a > 1) {
                        int q = a / m;
                        int t = m;
                        m = a % m;
                        a = t;
                        t = y;
                        y = x - q * y;
                        x = t;
                    }
                    if (x < 0) {
                        x += m0;
                    }
                    total = num[i] * x;
                    index = total % alfabet.length;
                }
            }
            reMessage.append(alfabet[index - 1]);
        }
        message = reMessage.substring(0, message.length());
        return message;
    }
}

package agha.digitalconverter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by User-Sai on 9/4/2017.
 */

public class Fraction extends Fragment {

    EditText dec, bin, hex, oct;
    TextView delete;

    public Fraction() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fraction, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        dec = (EditText) getView().findViewById(R.id.et_fra_dec);
        bin = (EditText) getView().findViewById(R.id.et_fra_bin);
        hex = (EditText) getView().findViewById(R.id.et_fra_hex);
        oct = (EditText) getView().findViewById(R.id.et_fra_oct);

        delete = (TextView) getView().findViewById(R.id.fra_clear);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dec.setText("");
                bin.setText("");
                hex.setText("");
                oct.setText("");
            }
        });

        dec.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                return;
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                return;
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (dec.isFocused()) {
                    String Decimal = dec.getText().toString();
                    if (hasPoint(Decimal) && !Decimal.equalsIgnoreCase(".")) {
                        dec.setBackground(getResources().getDrawable(R.drawable.main_textview));
                        bin.setText(fractionDecToBin(Double.parseDouble(Decimal)));
                        hex.setText(fractionDecToHex(Double.parseDouble(Decimal)));
                        oct.setText(fractionDecToOct(Double.parseDouble(Decimal)));
                    } else if (Decimal.equalsIgnoreCase(".")) {
                        bin.setText("");
                        hex.setText("");
                        oct.setText("");
                    } else {
                        if (!Decimal.isEmpty()) {
                            if (Double.parseDouble(Decimal) > Double.parseDouble("4294967295")) {
                                changeDrawableDec();
                                dec.setError("Supports only 32-bit");
                            } else {
                                dec.setBackground(getResources().getDrawable(R.drawable.main_textview));
                                bin.setText(USDecToBin(Long.parseLong(Decimal)));
                                hex.setText(USDecToHex(Long.parseLong(Decimal)));
                                oct.setText(USDecToOct(Long.parseLong(Decimal)));
                            }
                        } else {
                            dec.setBackground(getResources().getDrawable(R.drawable.main_textview));
                            bin.setText("");
                            hex.setText("");
                            oct.setText("");
                        }
                    }
                }
            }
        });

        bin.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                return;
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                return;
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (bin.isFocused()) {
                    String Binary = bin.getText().toString();
                    if (hasPoint(Binary) && !Binary.equalsIgnoreCase(".") && isFraBin(Binary)) {
                        bin.setBackground(getResources().getDrawable(R.drawable.main_textview));
                        dec.setText(fractionBinToDec(Binary) + "");
                        hex.setText(fractionBinToHex(Binary));
                        oct.setText(fractionBinToOct(Binary));
                    } else if (Binary.equalsIgnoreCase(".")) {
                        dec.setText("");
                        hex.setText("");
                        oct.setText("");
                    } else {
                        if (!Binary.isEmpty() && isFraBin(Binary)) {
                            bin.setBackground(getResources().getDrawable(R.drawable.main_textview));
                            dec.setText(USBinToDec(Binary) + "");
                            hex.setText(USBinToHex(Binary));
                            oct.setText(USBinToOct(Binary));
                        } else if (!isIntBin(Binary)) {
                            changeDrawableBin();
                            bin.setError("Binary accepts only 0 or 1");
                        } else {
                            bin.setBackground(getResources().getDrawable(R.drawable.main_textview));
                            dec.setText("");
                            hex.setText("");
                            oct.setText("");
                        }
                    }
                }
            }
        });

        hex.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                return;
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                return;
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (hex.isFocused()) {
                    String Hex = hex.getText().toString();
                    if (hasPoint(Hex) && !Hex.equalsIgnoreCase(".") && isFraHex(Hex)) {
                        hex.setBackground(getResources().getDrawable(R.drawable.main_textview));
                        dec.setText(fractionHexToDec(Hex) + "");
                        bin.setText(fractionHexToBin(Hex));
                        oct.setText(fractionHexToOct(Hex));
                    } else if (Hex.equalsIgnoreCase(".")) {
                        bin.setText("");
                        dec.setText("");
                        oct.setText("");
                    } else {
                        if (!Hex.isEmpty() && isFraHex(Hex)) {
                            hex.setBackground(getResources().getDrawable(R.drawable.main_textview));
                            dec.setText(USHexToDec(Hex) + "");
                            bin.setText(USHexToBin(Hex));
                            oct.setText(USHexToOct(Hex));
                        } else if (!isIntHex(Hex)) {
                            changeDrawableHex();
                            hex.setError("Input error");
                        } else {
                            hex.setBackground(getResources().getDrawable(R.drawable.main_textview));
                            dec.setText("");
                            bin.setText("");
                            oct.setText("");
                        }
                    }
                }
            }
        });

        oct.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                return;
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                return;
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (oct.isFocused()) {
                    String Oct = oct.getText().toString();
                    if (hasPoint(Oct) && !Oct.equalsIgnoreCase(".") && isFraOct(Oct)) {
                        oct.setBackground(getResources().getDrawable(R.drawable.main_textview));
                        dec.setText(fractionOctToDec(Oct) + "");
                        bin.setText(fractionOctToBin(Oct));
                        hex.setText(fractionOctToHex(Oct));
                    } else if (Oct.equalsIgnoreCase(".")) {
                        bin.setText("");
                        hex.setText("");
                        dec.setText("");
                    } else {
                        if (Oct.length() == 11 && (Oct.charAt(0) == '4' || Oct.charAt(0) == '5'
                                || Oct.charAt(0) == '6' || Oct.charAt(0) == '7')) {
                            changeDrawableOct();
                            oct.setError("Supports only 32-bit");
                        } else if (!Oct.isEmpty() && isFraOct(Oct)) {
                            oct.setBackground(getResources().getDrawable(R.drawable.main_textview));
                            dec.setText(USOctToDec(Oct) + "");
                            bin.setText(USOctToBin(Oct));
                            hex.setText(USOctToHex(Oct));
                        } else if (!isIntOct(Oct)) {
                            changeDrawableOct();
                            oct.setError("Input error");
                        } else {
                            oct.setBackground(getResources().getDrawable(R.drawable.main_textview));
                            dec.setText("");
                            bin.setText("");
                            hex.setText("");
                        }
                    }
                }
            }
        });


    }

    public boolean isIntOct(String oct) {
        for (int i = 0; i < oct.length(); i++)
            if (oct.charAt(i) != '0' && oct.charAt(i) != '1' &&
                    oct.charAt(i) != '2' && oct.charAt(i) != '3' &&
                    oct.charAt(i) != '4' && oct.charAt(i) != '5' &&
                    oct.charAt(i) != '6' && oct.charAt(i) != '7') return false;
        return true;
    }

    public boolean isIntHex(String hex) {
        hex = hex.toLowerCase();
        for (int i = 0; i < hex.length(); i++) {
            if (hex.charAt(i) != '0' && hex.charAt(i) != '1' &&
                    hex.charAt(i) != '2' && hex.charAt(i) != '3' &&
                    hex.charAt(i) != '4' && hex.charAt(i) != '5' &&
                    hex.charAt(i) != '6' && hex.charAt(i) != '7' &&
                    hex.charAt(i) != '8' && hex.charAt(i) != '9' &&
                    hex.charAt(i) != 'a' && hex.charAt(i) != 'b' &&
                    hex.charAt(i) != 'c' && hex.charAt(i) != 'd' &&
                    hex.charAt(i) != 'e' && hex.charAt(i) != 'f') return false;
        }
        return true;
    }

    public boolean isIntBin(String bin) {
        for (int i = 0; i < bin.length(); i++)
            if (bin.charAt(i) != '0' && bin.charAt(i) != '1') return false;
        return true;
    }

    public boolean hasPoint(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '.') return true;
        }
        return false;
    }

    private void changeDrawableDec() {
        dec.setBackground(getResources().getDrawable(R.drawable.error_main_textview));
        bin.setText("Error");
        hex.setText("Error");
        oct.setText("Error");
    }

    private void changeDrawableBin() {
        bin.setBackground(getResources().getDrawable(R.drawable.error_main_textview));
        dec.setText("Error");
        hex.setText("Error");
        oct.setText("Error");
    }

    private void changeDrawableHex() {
        hex.setBackground(getResources().getDrawable(R.drawable.error_main_textview));
        dec.setText("Error");
        bin.setText("Error");
        oct.setText("Error");
    }

    private void changeDrawableOct() {
        oct.setBackground(getResources().getDrawable(R.drawable.error_main_textview));
        dec.setText("Error");
        hex.setText("Error");
        bin.setText("Error");
    }

    // ** Unsigned Conversion ** //
    // From Decimal To Hexa
    public String USDecToHex(long dec) {
        return Long.toHexString(dec);
    }

    // From Decimal To Oct
    public String USDecToOct(long dec) {
        return Long.toOctalString(dec);
    }

    // From Decimal To Bin
    public String USDecToBin(long dec) {
        return Long.toBinaryString(dec);
    }

    // From Binary To Dec
    public long USBinToDec(String bin) {
        return Long.parseLong(bin, 2);
    }

    // From Binary To Hex
    public String USBinToHex(String bin) {
        // convert from bin to dec
        long binToDec = USBinToDec(bin);
        // convert from dec to hex
        String DecToHex = USDecToHex(binToDec);
        return DecToHex;
    }

    // From Binary To Oct
    public String USBinToOct(String bin) {
        // convert from bin to dec
        long binToDec = USBinToDec(bin);
        // convert from dec to hex
        String DecToOct = USDecToOct(binToDec);
        return DecToOct;
    }

    // From Hex To Dec
    public static long USHexToDec(String hex) {
        if (hex.isEmpty()) return 0;
        return Long.parseLong(hex, 16);
    }

    // From Hex To Bin
    public String USHexToBin(String hex) {
        if (hex.isEmpty()) return "";
        hex = hex.toLowerCase();

        Map hexToBinMap = new HashMap();
        hexToBinMap.put('0', "0000");
        hexToBinMap.put('1', "0001");
        hexToBinMap.put('2', "0010");
        hexToBinMap.put('3', "0011");
        hexToBinMap.put('4', "0100");
        hexToBinMap.put('5', "0101");
        hexToBinMap.put('6', "0110");
        hexToBinMap.put('7', "0111");
        hexToBinMap.put('8', "1000");
        hexToBinMap.put('9', "1001");
        hexToBinMap.put('a', "1010");
        hexToBinMap.put('b', "1011");
        hexToBinMap.put('c', "1100");
        hexToBinMap.put('d', "1101");
        hexToBinMap.put('e', "1110");
        hexToBinMap.put('f', "1111");

        String binaryValue = "";

        for (int i = 0; i < hex.length(); i++) {
            binaryValue += hexToBinMap.get(hex.charAt(i));
        }

        return binaryValue;
    }

    // From Hex To Oct
    public String USHexToOct(String hex) {
        if (hex.isEmpty()) return "";
        // convert to dec
        long dec = USHexToDec(hex);
        // convert from dec to octal
        return Long.toOctalString(dec);
    }

    // From Oct To Dec
    public long USOctToDec(String oct) {
        if (oct.isEmpty()) return 0;
        return Long.parseLong(oct, 8);
    }

    // From Oct To Bin
    public String USOctToBin(String oct) {
        if (oct.isEmpty()) return "";
        Map octToBinMap = new HashMap();
        octToBinMap.put('0', "000");
        octToBinMap.put('1', "001");
        octToBinMap.put('2', "010");
        octToBinMap.put('3', "011");
        octToBinMap.put('4', "100");
        octToBinMap.put('5', "101");
        octToBinMap.put('6', "110");
        octToBinMap.put('7', "111");

        String binValue = "";

        for (int i = 0; i < oct.length(); i++) {
            binValue += octToBinMap.get(oct.charAt(i));
        }

        return binValue;
    }

    // From Oct To Hex
    public String USOctToHex(String oct) {
        if (oct.isEmpty()) return "";
        // convert to dec
        long dec = USOctToDec(oct);
        // convert to hex
        return USDecToHex(dec);
    }

    // *** End of Unsigned *** //

    public static boolean isFraBin(String bin) {
        for (int i = 0; i < bin.length(); i++)
            if (bin.charAt(i) != '0' && bin.charAt(i) != '1' && bin.charAt(i) != '.') return false;
        return true;
    }

    public static boolean isFraOct(String oct) {
        for (int i = 0; i < oct.length(); i++)
            if (oct.charAt(i) != '0' && oct.charAt(i) != '1' &&
                    oct.charAt(i) != '2' && oct.charAt(i) != '3' &&
                    oct.charAt(i) != '4' && oct.charAt(i) != '5' &&
                    oct.charAt(i) != '6' && oct.charAt(i) != '7' &&
                    oct.charAt(i) != '.') return false;
        return true;
    }

    public static boolean isFraHex(String hex) {
        for (int i = 0; i < hex.length(); i++) {
            if (hex.charAt(i) != '0' && hex.charAt(i) != '1' &&
                    hex.charAt(i) != '2' && hex.charAt(i) != '3' &&
                    hex.charAt(i) != '4' && hex.charAt(i) != '5' &&
                    hex.charAt(i) != '6' && hex.charAt(i) != '7' &&
                    hex.charAt(i) != '8' && hex.charAt(i) != '9' &&
                    hex.charAt(i) != 'a' && hex.charAt(i) != 'b' &&
                    hex.charAt(i) != 'c' && hex.charAt(i) != 'd' &&
                    hex.charAt(i) != 'e' && hex.charAt(i) != 'f' &&
                    hex.charAt(i) != '.') return false;
        }
        return true;
    }

    // *** Fraction *** ///
    public String fractionDecToBin(double dec) {
        String decStr = "" + dec;
        String number = Long.toBinaryString((long) dec);
        String fractionStr = "0" + decStr.substring(decStr.indexOf("."));
        double fraction = Double.parseDouble(fractionStr);
        String resFra = "";
        String res = "";
        while (fraction != 0) {
            fraction = fraction * 2;
            res += String.valueOf(fraction).substring(0, 1);
            resFra += res;
            if (res.equals("1")) {
                fraction -= 1;
            }
            res = "";
        }
        return number + "." + resFra;
    }

    public String fractionDecToHex(double dec) {
        String binRep = fractionDecToBin(dec);
        String number = binRep.substring(0, binRep.indexOf("."));
        String fraction = binRep.substring(binRep.indexOf(".") + 1);

        String numHexRep = USBinToHex(number);
        int zeros = 4 - (fraction.length() % 4);

        for (int i = 0; i < zeros; i++) {
            fraction += "0";
        }

        String fraHexRep = "";
        int j = fraction.length()/4 ;
        for (int i = 0 ; i<j ; i++){
            String tmp = fraction.substring(0,4);
            fraction = fraction.substring(4);
            if (tmp.equals("0000")) fraHexRep+="0"; else if (tmp.equals("0001")) fraHexRep+="1"; else if (tmp.equals("0010")) fraHexRep+="2";
            else if (tmp.equals("0011")) fraHexRep+="3"; else if (tmp.equals("0100")) fraHexRep+="4"; else if (tmp.equals("0101")) fraHexRep+="5";
            else if (tmp.equals("0110")) fraHexRep+="6"; else if (tmp.equals("0111")) fraHexRep+="7"; else if (tmp.equals("1000")) fraHexRep+="8";
            else if (tmp.equals("1001")) fraHexRep+="9"; else if (tmp.equals("1010")) fraHexRep+="a"; else if (tmp.equals("1011")) fraHexRep+="b";
            else if (tmp.equals("1100")) fraHexRep+="c"; else if (tmp.equals("1101")) fraHexRep+="d"; else if (tmp.equals("1110")) fraHexRep+="e";
            else if (tmp.equals("1111")) fraHexRep+="f";
        }

        return cleanZero(numHexRep + "." + fraHexRep);
    }

    public String fractionDecToOct(double dec) {
        String binRep = fractionDecToBin(dec);
        String number = binRep.substring(0, binRep.indexOf("."));
        String fraction = binRep.substring(binRep.indexOf(".") + 1);

        String numOctRep = USBinToOct(number);
        int zeros = 3 - (fraction.length() % 3);

        for (int i = 0; i < zeros; i++) {
            fraction += "0";
        }

        String fraOctRep = "";
        int j = fraction.length()/3 ;
        for (int i = 0 ; i<j ; i++){
            String tmp = fraction.substring(0,3);
            fraction = fraction.substring(3);
            if (tmp.equals("000")) fraOctRep+="0"; else if (tmp.equals("001")) fraOctRep+="1"; else if (tmp.equals("010")) fraOctRep+="2";
            else if (tmp.equals("011")) fraOctRep+="3"; else if (tmp.equals("100")) fraOctRep+="4"; else if (tmp.equals("101")) fraOctRep+="5";
            else if (tmp.equals("110")) fraOctRep+="6"; else if (tmp.equals("111")) fraOctRep+="7";
        }

        return cleanZero(numOctRep + "." + fraOctRep);
    }

    public double fractionBinToDec(String bin) {
        String numberStr = bin.substring(0, bin.indexOf("."));
        if (numberStr.equals("")) numberStr = "0";
        String fractionStr = bin.substring(bin.indexOf(".") + 1);

        long number = Long.parseLong(numberStr, 2);
        int power = -1;
        double fraction = 0;

        for (int i = 0; i < fractionStr.length(); i++) {
            if (fractionStr.charAt(i) == '1') {
                fraction += Math.pow(2, power);
            }
            power--;
        }
        return number + fraction;
    }

    public String fractionBinToHex(String bin) {
        return cleanZero(fractionDecToHex(fractionBinToDec(bin)));
    }

    public String fractionBinToOct(String bin) {
        return cleanZero(fractionDecToOct(fractionBinToDec(bin)));
    }

    public double fractionHexToDec(String hex) {
        String number = hex.substring(0, hex.indexOf("."));
        String hexFraction = hex.substring(hex.indexOf(".") + 1);

        double numberDec = USHexToDec(number);
        String binFraction = USHexToBin(hexFraction);
        double fraction = calcFraction(binFraction);

        return numberDec + fraction;
    }

    public String fractionHexToBin(String hex) {
        String hexNumber = hex.substring(0, hex.indexOf("."));
        String hexFraction = hex.substring(hex.indexOf(".") + 1);

        String binNumber = USHexToBin(hexNumber);
        String binFraction = USHexToBin(hexFraction);

        return binNumber + "." + binFraction;
    }

    public String fractionHexToOct(String hex) {
        return cleanZero(fractionDecToOct(Double.parseDouble(dec.getText().toString())));
    }

    public double fractionOctToDec(String oct) {
        String octNumber = oct.substring(0, oct.indexOf("."));
        String octFraction = oct.substring(oct.indexOf(".") + 1);

        double numberDec = USOctToDec(octNumber);
        String binFraction = USOctToBin(octFraction);
        double fraction = calcFraction(binFraction);

        return numberDec + fraction;
    }

    public String fractionOctToBin(String oct) {
        String octNumber = oct.substring(0, oct.indexOf("."));
        String octFraction = oct.substring(oct.indexOf(".") + 1);

        String binNumber = USOctToBin(octNumber);
        String binFraction = USOctToBin(octFraction);

        return cleanZero(binNumber + "." + binFraction);
    }

    public String fractionOctToHex(String oct) {
        return cleanZero(fractionDecToHex(Double.parseDouble(dec.getText().toString())));
    }

    public double calcFraction(String fractionStr) {
        double fraction = 0;
        int power = -1;

        for (int i = 0; i < fractionStr.length(); i++) {
            if (fractionStr.charAt(i) == '1') {
                fraction += Math.pow(2, power);
            }
            power--;
        }
        return fraction;
    }

    public String cleanZero(String clean){
        Log.e("Clean",clean);
        for (int i = 0 ; i<clean.length() ; i++){
            if (clean.charAt(0) != '0') break ;
            else clean = clean.substring(1);
        }
        for (int i = clean.length()-1 ; i>=0 ; i--){
            if (clean.charAt(clean.length()-1) != '0' || clean.charAt(clean.length()-1) != '.') break ;
            else clean = clean.substring(0,clean.length()-1);
        } return clean ;
    }


}

package agha.digitalconverter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Andulrhman Hasan Aghaon 9/4/2017.
 */

public class SignedInt extends Fragment {

    EditText dec, s2, hex, sm;
    TextView delete;

    public SignedInt() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_signed, container, false);
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        dec = (EditText) getView().findViewById(R.id.et_sign_dec);
        s2 = (EditText) getView().findViewById(R.id.et_sign_s2);
        hex = (EditText) getView().findViewById(R.id.et_sign_hex);
        sm = (EditText) getView().findViewById(R.id.et_sign_sm);

        delete = (TextView) getView().findViewById(R.id.sign_clear);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dec.setText("");
                s2.setText("");
                hex.setText("");
                sm.setText("");
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
                    if (hasPoint(Decimal)) {
                        changeDrawableDec();
                        dec.setError("Fractions are not allowed");
                    } else {
                        if (!Decimal.isEmpty() && !Decimal.equalsIgnoreCase("-")) {
                            if (Long.parseLong(Decimal) > Long.parseLong("2147483647") || Long.parseLong(Decimal) < Long.parseLong("-2147483647")) {
                                changeDrawableDec();
                                dec.setError("Supports only 32-bit");
                            } else {
                                dec.setBackground(getResources().getDrawable(R.drawable.main_textview));
                                s2.setText(SDecTo2s(Long.parseLong(Decimal)));
                                hex.setText(SDecToHex(Long.parseLong(Decimal)));
                                sm.setText(SDecToSM(Long.parseLong(Decimal)));
                            }
                        } else {
                            dec.setBackground(getResources().getDrawable(R.drawable.main_textview));
                            s2.setText("");
                            hex.setText("");
                            sm.setText("");
                        }
                    }
                }
            }
        });

        s2.addTextChangedListener(new TextWatcher() {
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
                if (s2.isFocused()) {
                    String S2 = s2.getText().toString();
                    if (hasPoint(S2)) {
                        changeDrawableDec();
                        s2.setError("Fractions are not allowed");
                    } else {
                        if (!S2.isEmpty() && isIntBin(S2)) {
                            if (Long.parseLong(S2) > Long.parseLong("2147483647") || Long.parseLong(S2) < Long.parseLong("-2147483647")) {
                                changeDrawableDec();
                                s2.setError("Supports only 32-bit");
                            } else {
                                s2.setBackground(getResources().getDrawable(R.drawable.main_textview));
                                dec.setText(S2sToDec(S2) + "");
                                hex.setText(S2sToHex(S2));
                                sm.setText(S2sToSM(S2));
                            }
                        } else if (!isIntBin(S2)) {
                            changeDrawableS2();
                            s2.setError("Binary accepts only 0 or 1");
                        } else {
                            s2.setBackground(getResources().getDrawable(R.drawable.main_textview));
                            dec.setText("");
                            hex.setText("");
                            sm.setText("");
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
                    if (hasPoint(Hex)) {
                        changeDrawableDec();
                        hex.setError("Fractions are not allowed");
                    } else {
                        if (!Hex.isEmpty() && isIntHex(Hex)) {
                            if (Long.parseLong(Hex) > Long.parseLong("2147483647") || Long.parseLong(Hex) < Long.parseLong("-2147483647")) {
                                changeDrawableHex();
                                hex.setError("Supports only 32-bit");
                            } else {
                                hex.setBackground(getResources().getDrawable(R.drawable.main_textview));
                                s2.setText(SHexTo2s(Hex));
                                dec.setText(SHexToDec(Hex) + "");
                                sm.setText(SHexToSM(Hex));
                            }
                        } else if (!isIntHex(Hex)) {
                            changeDrawableHex();
                            hex.setError("Input error");
                        } else {
                            hex.setBackground(getResources().getDrawable(R.drawable.main_textview));
                            s2.setText("");
                            dec.setText("");
                            sm.setText("");
                        }
                    }
                }
            }
        });

        sm.addTextChangedListener(new TextWatcher() {
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
                if (sm.isFocused()) {
                    String SM = sm.getText().toString();
                    if (hasPoint(SM)) {
                        changeDrawableSm();
                        sm.setError("Fractions are not allowed");
                    } else {
                        if (!SM.isEmpty()) {
                            if (Long.parseLong(SM) > Long.parseLong("2147483647") || Long.parseLong(SM) < Long.parseLong("-2147483647")) {
                                changeDrawableSm();
                                sm.setError("Supports only 32-bit");
                            } else {
                                sm.setBackground(getResources().getDrawable(R.drawable.main_textview));
                                s2.setText(SSMTo2s(SM));
                                dec.setText(SSMToDec(SM) + "");
                                hex.setText(SSMToHex(SM));
                            }
                        } else if (!isIntBin(SM)) {
                            changeDrawableS2();
                            s2.setError("Binary accepts only 0 or 1");
                        } else {
                            sm.setBackground(getResources().getDrawable(R.drawable.main_textview));
                            s2.setText("");
                            dec.setText("");
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
        s2.setText("Error");
        hex.setText("Error");
        sm.setText("Error");
    }

    private void changeDrawableS2() {
        s2.setBackground(getResources().getDrawable(R.drawable.error_main_textview));
        dec.setText("Error");
        hex.setText("Error");
        sm.setText("Error");
    }

    private void changeDrawableHex() {
        hex.setBackground(getResources().getDrawable(R.drawable.error_main_textview));
        dec.setText("Error");
        s2.setText("Error");
        sm.setText("Error");
    }

    private void changeDrawableSm() {
        sm.setBackground(getResources().getDrawable(R.drawable.error_main_textview));
        dec.setText("Error");
        hex.setText("Error");
        s2.setText("Error");
    }


    // *** Signed Conversion *** //
    // From Dec To 2's
    public String SDecTo2s(long dec) {
        if (dec == 0) {
            return "0";
        } else if (dec == -1) {
            return "11111";
        } else if (dec < Integer.MIN_VALUE) {
            return "Cannot be represented in 32 bits";
        } else if (dec > Integer.MAX_VALUE) {
            return "Cannot be represented in 32 bits";
        } else if (dec > 0) {
            return "0" + USDecToBin(dec);
        }

        String binaryValue = USDecToBin(dec);

        for (int i = 0; i < binaryValue.length() - 1; i++) {
            if (binaryValue.charAt(i) == '1' && binaryValue.charAt(i + 1) == '0') {
                return binaryValue.substring(i);
            }
        }

        return "";
    }

    // From Dec To Hex
    public String SDecToHex(long dec) {
        String hex = Long.toHexString(dec);
        if (dec < 0) {
            return hex.substring(8);
        } else return hex;
    }

    // From Dec To SM
    public String SDecToSM(long dec) {
        if (dec > 0) {
            return "0" + USDecToBin(dec);
        } else {
            dec = dec * -1;
            return "1" + USDecToBin(dec);
        }
    }

    // From 2s To Dec
    public long S2sToDec(String s2) {
        if (s2.charAt(0) == '0') {
            return USBinToDec(s2);
        } else {
            int power = s2.length() - 1;
            double DecValue = Math.pow(2, power) * -1;
            power--;
            for (int i = 1; i < s2.length(); i++) {
                if (s2.charAt(i) == '1') {
                    DecValue += Math.pow(2, power);
                }
                power--;
            }
            int DecResult = (int) DecValue;
            return DecResult;
        }
    }

    // From 2s To Hex
    public String S2sToHex(String s2) {
        long dec = S2sToDec(s2);
        return SDecToHex(dec);
    }

    // From 2s To SM
    public String S2sToSM(String s2) {
        long dec = S2sToDec(s2);
        return SDecToSM(dec);
    }

    // From Hex to Dec
    public long SHexToDec(String hex) {
        String binaryRep = USHexToBin(hex);
        return S2sToDec(binaryRep);
    }

    // From Hex to 2s
    public String SHexTo2s(String hex) {
        return USHexToBin(hex);
    }

    // From Hex to SM
    public String SHexToSM(String hex) {
        long dec = SHexToDec(hex);
        return SDecToSM(dec);
    }

    // From SM to Dec
    public long SSMToDec(String sm) {
        if (sm.charAt(0) == '0') {
            return USBinToDec(sm);
        } else {
            sm = sm.substring(1);
            long decRes = USBinToDec(sm);
            decRes = decRes * -1;
            return decRes;
        }
    }

    // From SM to 2s
    public String SSMTo2s(String sm) {
        long dec = SSMToDec(sm);
        return SDecTo2s(dec);
    }

    // From SM to Hex
    public String SSMToHex(String sm) {
        long dec = SSMToDec(sm);
        return SDecToHex(dec);
    }

    // *** end of signed *** //
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
        return Long.parseLong(hex, 16);
    }

    // From Hex To Bin
    public String USHexToBin(String hex) {
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
        // convert to dec
        long dec = USHexToDec(hex);
        // convert from dec to octal
        return Long.toOctalString(dec);
    }

    // From Oct To Dec
    public long USOctToDec(String oct) {
        return Long.parseLong(oct, 8);
    }

    // From Oct To Bin
    public String USOctToBin(String oct) {
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
        // convert to dec
        long dec = USOctToDec(oct);
        // convert to hex
        return USDecToHex(dec);
    }

    // *** End of Unsigned *** //


}

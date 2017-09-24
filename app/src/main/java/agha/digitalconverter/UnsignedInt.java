package agha.digitalconverter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Abdulrhman Hasan Agha on 9/4/2017.
 */

public class UnsignedInt extends Fragment {

    EditText dec, bin, hex, oct;
    TextView delete;

    public UnsignedInt() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_unsigned, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        dec = (EditText) getView().findViewById(R.id.et_un_dec);
        bin = (EditText) getView().findViewById(R.id.et_un_bin);
        hex = (EditText) getView().findViewById(R.id.et_un_hex);
        oct = (EditText) getView().findViewById(R.id.et_un_oct);

        delete = (TextView) getView().findViewById(R.id.un_clear);

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
                    if (hasPoint(Decimal)) {
                        changeDrawableDec();
                        dec.setError("Fractions are not allowed");
                    } else {
                        if (!Decimal.isEmpty()) {
                            if (Long.parseLong(Decimal) > Long.parseLong("4294967295")) {
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
                    if (hasPoint(Binary)) {
                        changeDrawableBin();
                        bin.setError("Fractions are not allowed");
                    } else {
                        if (!Binary.isEmpty() && isIntBin(Binary)) {
                            bin.setBackground(getResources().getDrawable(R.drawable.main_textview));
                            dec.setText(USBinToDec(Binary) + "");
                            hex.setText(USBinToHex(Binary));
                            oct.setText(USBinToOct(Binary));
                        } else if (!isIntBin(Binary)){
                            changeDrawableBin();
                            bin.setError("Binary accepts only 0 or 1");
                        } else{
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
                    if (hasPoint(Hex)) {
                        changeDrawableHex();
                        hex.setError("Fractions are not allowed");
                    } else {
                        if (!Hex.isEmpty() && isIntHex(Hex)) {
                            hex.setBackground(getResources().getDrawable(R.drawable.main_textview));
                            dec.setText(USHexToDec(Hex) + "");
                            bin.setText(USHexToBin(Hex));
                            oct.setText(USHexToOct(Hex));
                        } else if (!isIntHex(Hex)){
                            changeDrawableHex();
                            hex.setError("Input error");
                        } else{
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
                    if (hasPoint(Oct)) {
                        changeDrawableOct();
                        oct.setError("Fractions are not allowed");
                    } else {
                        if (Oct.length() == 11 && (Oct.charAt(0) == '4' || Oct.charAt(0) == '5'
                        || Oct.charAt(0) == '6' || Oct.charAt(0) == '7')){
                            changeDrawableOct();
                            oct.setError("Supports only 32-bit");
                        }
                        else if (!Oct.isEmpty() && isIntOct(Oct)) {
                            oct.setBackground(getResources().getDrawable(R.drawable.main_textview));
                            dec.setText(USOctToDec(Oct) + "");
                            bin.setText(USOctToBin(Oct));
                            hex.setText(USOctToHex(Oct));
                        } else if (!isIntOct(Oct)){
                            changeDrawableOct();
                            oct.setError("Input error");
                        } else{
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

    public boolean isIntOct(String oct){
        for (int i = 0 ; i <oct.length() ; i++)
            if (oct.charAt(i) != '0' && oct.charAt(i) != '1' &&
                    oct.charAt(i) != '2' && oct.charAt(i) != '3' &&
                    oct.charAt(i) != '4' && oct.charAt(i) != '5' &&
                    oct.charAt(i) != '6' && oct.charAt(i) != '7') return false;
        return true ;
    }

    public boolean isIntHex(String hex){
        hex = hex.toLowerCase();
        for (int i = 0 ; i<hex.length(); i++){
            if (hex.charAt(i) != '0' && hex.charAt(i) != '1' &&
                    hex.charAt(i) != '2' && hex.charAt(i) != '3' &&
                    hex.charAt(i) != '4' && hex.charAt(i) != '5' &&
                    hex.charAt(i) != '6' && hex.charAt(i) != '7' &&
                    hex.charAt(i) != '8' && hex.charAt(i) != '9' &&
                    hex.charAt(i) != 'a' && hex.charAt(i) != 'b' &&
                    hex.charAt(i) != 'c' && hex.charAt(i) != 'd' &&
                    hex.charAt(i) != 'e' && hex.charAt(i) != 'f') return false ;
        }
        return true ;
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

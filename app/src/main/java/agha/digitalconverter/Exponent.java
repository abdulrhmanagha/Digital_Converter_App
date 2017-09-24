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

/**
 * Created by User-Sai on 9/4/2017.
 */

public class Exponent extends Fragment {

    EditText dec, sign, exp, fraction;
    TextView delete;

    public Exponent() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_exp, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        dec = (EditText) getView().findViewById(R.id.et_exp_dec);
        sign = (EditText) getView().findViewById(R.id.et_exp_sign);
        exp = (EditText) getView().findViewById(R.id.et_exp_exp);
        fraction = (EditText) getView().findViewById(R.id.et_exp_bin);

        delete = (TextView) getView().findViewById(R.id.exp_clear);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dec.setText("");
                sign.setText("");
                exp.setText("");
                fraction.setText("");
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
                    if (!hasPoint(Decimal)) Decimal = Decimal + ".0";
                    if (hasPoint(Decimal) && !Decimal.equalsIgnoreCase(".")) {
                        if (Double.parseDouble(Decimal) < 0) {
                            dec.setBackground(getResources().getDrawable(R.drawable.main_textview));
                            sign.setText(expDecToFractionSign(Double.parseDouble(Decimal)));
                            exp.setText(expDecToFractionExp(Double.parseDouble(Decimal)*-1));
                            fraction.setText(expDecToFractionSignificand(Double.parseDouble(Decimal)*-1));
                        } else {
                            dec.setBackground(getResources().getDrawable(R.drawable.main_textview));
                            sign.setText(expDecToFractionSign(Double.parseDouble(Decimal)));
                            exp.setText(expDecToFractionExp(Double.parseDouble(Decimal)));
                            fraction.setText(expDecToFractionSignificand(Double.parseDouble(Decimal)));
                        }
                    } else if (Decimal.equalsIgnoreCase(".") || Decimal.equalsIgnoreCase("-")) {
                        sign.setText("");
                        exp.setText("");
                        fraction.setText("");
                    } else {
                        if (!Decimal.isEmpty()) {
                            if (Long.parseLong(Decimal) > Long.parseLong("2147483647") || Long.parseLong(Decimal) < Long.parseLong("-2147483647")) {
                                changeDrawableDec();
                                dec.setError("Supports only 32-bit");
                            } else {
                                dec.setBackground(getResources().getDrawable(R.drawable.main_textview));
                                Log.e("DEC","HERE");
                                sign.setText(expDecToFractionSign(Double.parseDouble(Decimal)));
                                Log.e("s","HERE");
                                exp.setText(expDecToFractionExp(Long.parseLong(Decimal)));
                                Log.e("exp","HERE");
                                fraction.setText(expDecToFractionSignificand(Long.parseLong(Decimal)));
                                Log.e("frac","HERE");
                                Log.e("0","---------------------------");
                            }
                        } else {
                            dec.setBackground(getResources().getDrawable(R.drawable.main_textview));
                            sign.setText("");
                            exp.setText("");
                            fraction.setText("");
                        }
                    }
                }
            }
        });

        sign.addTextChangedListener(new TextWatcher() {
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
                if (sign.isFocused()) {
                    String Sign = sign.getText().toString();
                    if (!isIntBin(Sign)) {
                        changeDrawableSign();
                        sign.setError("Accepts only 0 or 1");
                    } else if (isIntBin(Sign) || Sign.isEmpty()) {
                        sign.setBackground(getResources().getDrawable(R.drawable.main_textview));
                        dec.setText("");
                        //fraction.setText("");
                        //exp.setText("");
                    }
                    if (!fraction.getText().toString().isEmpty() && !exp.getText().toString().isEmpty() &&
                            isIntBin(fraction.getText().toString()) && isIntBin(exp.getText().toString())) {
                        dec.setText(expFractionToDec(Sign, exp.getText().toString(), fraction.getText().toString()));
                    }
                }
            }
        });

        exp.addTextChangedListener(new TextWatcher() {
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
                if (exp.isFocused()) {
                    String Exp = exp.getText().toString();
                    if (!isIntBin(Exp)) {
                        changeDrawableExp();
                        exp.setError("Accepts only 0 or 1");
                    } else if (isIntBin(Exp) || Exp.isEmpty()) {
                        exp.setBackground(getResources().getDrawable(R.drawable.main_textview));
                        dec.setText("");
                        //fraction.setText("");
                        //sign.setText("");
                    }
                    if (!fraction.getText().toString().isEmpty() && !sign.getText().toString().isEmpty() &&
                            isIntBin(fraction.getText().toString()) && isIntBin(sign.getText().toString())) {
                        dec.setText(expFractionToDec(sign.getText().toString(), Exp, fraction.getText().toString()));
                    }
                }
            }
        });

        fraction.addTextChangedListener(new TextWatcher() {
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
                if (fraction.isFocused()) {
                    String Fraction = fraction.getText().toString();
                    if (!isIntBin(Fraction)) {
                        changeDrawableFraction();
                        fraction.setError("Accepts only 0 or 1");
                    } else if (isIntBin(Fraction) || Fraction.isEmpty()) {
                        fraction.setBackground(getResources().getDrawable(R.drawable.main_textview));
                        dec.setText("");
                        //exp.setText("");
                        //sign.setText("");
                    }
                    if (!exp.getText().toString().isEmpty() && !sign.getText().toString().isEmpty() &&
                            isIntBin(exp.getText().toString()) && isIntBin(sign.getText().toString())) {
                        dec.setText(expFractionToDec(sign.getText().toString(), exp.getText().toString(), Fraction));
                    }
                }
            }
        });

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
        sign.setText("Error");
        exp.setText("E");
        fraction.setText("");
    }

    private void changeDrawableSign() {
        sign.setBackground(getResources().getDrawable(R.drawable.error_main_textview));
        dec.setText("Error");
        exp.setText("E");
        fraction.setText("");
    }

    private void changeDrawableExp() {
        exp.setBackground(getResources().getDrawable(R.drawable.error_main_textview));
        dec.setText("Error");
        sign.setText("E");
        fraction.setText("");
    }

    private void changeDrawableFraction() {
        fraction.setBackground(getResources().getDrawable(R.drawable.error_main_textview));
        dec.setText("Error");
        exp.setText("Error");
        sign.setText("Error");
    }

    // ** Exp. ** //
    public String expFractionToDec(String sign, String exp, String fraction) {

        if (exp.length() < 8) {
            int zeros = 8 - exp.length();
            for (int i = 0; i < zeros; i++) {
                exp = exp + "0";
            }
        }
        if (fraction.length() < 23) {
            int zeros = 23 - exp.length();
            for (int i = 0; i < zeros; i++) {
                fraction = fraction + "0";
            }
        }

        // zero
        if (USBinToDec(exp) == 0 && USBinToDec(fraction) == 0) {
            return "0";
        }
        // infinity
        if (USBinToDec(exp) == 255 && USBinToDec(fraction) == 0) {
            return "INFINITY";
        }
        // NaN
        if (USBinToDec(exp) == 255 && USBinToDec(fraction) != 0) {
            return "NaN";
        }
        // Denormalized
        if (USBinToDec(exp) == 0 && USBinToDec(fraction) != 0) {
            double significand = 0 + calcFraction(fraction);
            double value = significand * Math.pow(2, -126);
            if (sign.equals("1")) {
                //return (value * -1 + "").substring(0, 8);
                return (value * -1 + "");
            } else {
                //return (value + "").substring(0, 8);
                return (value + "");
            }
        } else {
            int E = (int) USBinToDec(exp) - 127;
            double significand = 1 + calcFraction(fraction);
            double value = significand * Math.pow(2, E);
            if (sign.equals("1")) {
                //return (value * -1 + "").substring(0, 8);
                return (value * -1 + "");
            } else {
                //return (value + "").substring(0, 8);
                return (value + "");
            }
        }

    }

    // From Binary To Dec
    public long USBinToDec(String bin) {
        return Long.parseLong(bin, 2);
    }

    public String expDecToFractionSign(double dec) {
        if (dec < 0) {
            return "1";
        } else {
            return "0";
        }
    }

    public String expDecToFractionExp(double dec) {

        String bin = fractionDecToBin((long)dec);
        if (hasPoint(bin)) bin = bin.substring(bin.indexOf(".") + 1);
        if (dec == 0) {
            return "00000000";
        }

        int power = -1;

        if (dec < 1) {
            for (int i = 0; i < bin.length(); i++) {
                if (bin.charAt(i) == '1') {
                    break;
                } else {
                    power--;
                }
            }
        } else {
            String len = fractionDecToBin(dec);
            power = len.substring(0, len.indexOf(".")).length() - 1;
        }
        String result = USDecToBin(127 + power);
        if (result.length() < 8) {
            int rem = 8 - result.length();
            for (int i = 0; i < rem; i++) {
                result = "0" + result;
            }
        }
        return result ;
    }

    public String expDecToFractionSignificand(double dec) {
        String bin = fractionDecToBin(dec);

        if (dec < 1) {
            if (hasPoint(bin)) bin = bin.substring(bin.indexOf(".") + 1);
            for (int i = 0; i < bin.length(); i++) {
                if (bin.charAt(i) == '1') {
                    bin = bin.substring(i + 1);
                    break;
                }
            }
        } else {
            bin = bin.replace(".", "");
            bin = bin.substring(1);
        }

        if (bin.length() < 23) {
            int rem = 23 - bin.length();
            for (int i = 0; i < rem; i++) {
                bin = bin + "0";
            }
        }
        return bin;
    }

    public String fractionDecToBin(double dec) {
        String decStr = String.format("%.14f",dec);;
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

    // From Decimal To Bin
    public String USDecToBin(long dec) {
        return Long.toBinaryString(dec);
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
}

package com.example.scientificcalculator;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import org.mariuszgromada.math.mxparser.Expression;

import java.util.Stack;

public class MainActivity extends Activity {
    LinearLayout sciPanel;
    TextView quesDisplay, btnOne, btnTwo, btnThree, btnFour, btnFive, btnSix, btnSeven, btnEight, btnNine, btn0, btn00, btnDec, btnEqual, btnPlus, btnMinus, btnMulti, btnDiv, btnPercent, btnC, btnSin, btnCos, btnTan, btnLog, btnIn, btnBracket1, btnBracket2, btnPower, btnRoot, btnFacto, btnPie, btnE, btnINV, btnRAD, btnDEG, ansDisplay;
    ImageView slide, btnBack, btnHistory;
    float y1, y2;
    String toCalculate = "";
    Stack<String> toCalStack = new Stack<String>();
    Stack<String> quesStack = new Stack<String>();
    int isInverse = 0, bracketCount = 1;
    boolean isRadian = true, enabledE = false, isBracketOpen = false;
    int[] ids = new int[]{R.id.btnOne, R.id.btnTwo, R.id.btnThree, R.id.btnFour, R.id.btnFive, R.id.btnSix, R.id.btnSeven, R.id.btnEight, R.id.btnNine, R.id.btn0, R.id.btn00, R.id.btnDeci, R.id.btnEqual, R.id.btnPlus, R.id.btnMinus, R.id.btnMulti, R.id.btnDiv, R.id.btnPercent, R.id.btnC};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        quesDisplay = findViewById(R.id.quesDisplay);
        ansDisplay = findViewById(R.id.ansDisplay);
        btnHistory = findViewById(R.id.btnHistory);

        btnSin = findViewById(R.id.btnSin);
        btnCos = findViewById(R.id.btnCos);
        btnTan = findViewById(R.id.btnTan);
        btnLog = findViewById(R.id.btnLog);
        btnIn = findViewById(R.id.btnIn);
        btnBracket1 = findViewById(R.id.btnBracket1);
        btnBracket2 = findViewById(R.id.btnBracket2);
        btnPower = findViewById(R.id.btnPower);
        btnRoot = findViewById(R.id.btnRoot);
        btnFacto = findViewById(R.id.btnFacto);
        btnPie = findViewById(R.id.btnPie);
        btnE = findViewById(R.id.btnE);
        btnINV = findViewById(R.id.btnINV);
        btnRAD = findViewById(R.id.btnRAD);
        btnDEG = findViewById(R.id.btnDEG);

        sciPanel = findViewById(R.id.sciPanel);
        slide = findViewById(R.id.slide);
        btn0 = findViewById(R.id.btn0);
        btn00 = findViewById(R.id.btn00);
        btnOne = findViewById(R.id.btnOne);
        btnTwo = findViewById(R.id.btnTwo);
        btnThree = findViewById(R.id.btnThree);
        btnFour = findViewById(R.id.btnFour);
        btnFive = findViewById(R.id.btnFive);
        btnSix = findViewById(R.id.btnSix);
        btnSeven = findViewById(R.id.btnSeven);
        btnEight = findViewById(R.id.btnEight);
        btnNine = findViewById(R.id.btnNine);
        btnDec = findViewById(R.id.btnDeci);
        btnEqual = findViewById(R.id.btnEqual);
        btnPlus = findViewById(R.id.btnPlus);
        btnMinus = findViewById(R.id.btnMinus);
        btnMulti = findViewById(R.id.btnMulti);
        btnDiv = findViewById(R.id.btnDiv);
        btnBack = findViewById(R.id.btnBack);
        btnPercent = findViewById(R.id.btnPercent);
        btnC = findViewById(R.id.btnC);

        toShowSciPanel();

        btnSin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isInverse == 0) {
                    myInput("sin(");
                } else {
                    myInput("sin\u207B\u00B9(");
                }
                enabledE = false;
            }
        });

        btnCos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isInverse == 0) {
                    myInput("cos(");
                } else {
                    myInput("cos\u207B\u00B9(");
                }
                enabledE = false;
            }
        });

        btnTan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isInverse == 0) {
                    myInput("tan(");
                } else {
                    myInput("arctan(");
                    toCalStack.push("arctan(");
                }
                enabledE = false;
            }
        });

        btnLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isInverse == 0) {
                    myInput("log(");
                } else {
                    myInput("10^");
                    toCalStack.push("10^");
                }
            }
        });

        btnIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isInverse == 0) {
                    myInput("ln(");
                    toCalStack.push("ln(");
                } else {
                    enabledE = true;
                    myInput("e");
                }
            }
        });

        btnBracket1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myInput("(");
                toCalStack.push("(");
            }
        });

        btnBracket2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myInput(")");
            }
        });

        btnPower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myInput("^");
                toCalStack.push("^");
            }
        });

        btnRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myInput("√");
                toCalStack.push("√");
            }
        });

        btnFacto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myInput("!");
                toCalStack.push("!");
            }
        });

        btnPie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myInput("π");
                toCalStack.push("π");
            }
        });

        btnE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myInput("e");
            }
        });

        btnINV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isInverse == 1) {
                    btnSin.setText("sin");
                    btnCos.setText("cos");
                    btnTan.setText("tan");
                    btnLog.setText("log");
                    btnIn.setText("ln");
                    btnRoot.setText("√");
                    isInverse = 0;
                } else {
                    btnSin.setText(Html.fromHtml("sin<sup><small>-1</small></font></sup>"));
                    btnCos.setText(Html.fromHtml("cos<sup><small>-1</small></sup>"));
                    btnTan.setText(Html.fromHtml("tan<sup><small>-1</small></sup>"));
                    btnLog.setText(Html.fromHtml("10<sup><small>^</small></sup>"));
                    btnIn.setText(Html.fromHtml("e<sup><small>x</small></sup>"));
                    btnRoot.setText(Html.fromHtml("x<sup><small>2</small></sup>"));
                    isInverse = 1;
                }
            }
        });

        btnRAD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isRadian) {
                    isRadian = true;
                    btnRAD.setTextColor(Color.parseColor("#4796B4"));
                    btnDEG.setTextColor(Color.parseColor("#717578"));
                }
            }
        });

        btnDEG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isRadian = false;
                btnRAD.setTextColor(Color.parseColor("#717578"));
                btnDEG.setTextColor(Color.parseColor("#4796B4"));
            }
        });

        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myInput("0");
                toCalStack.push("0");
            }
        });

        btn00.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myInput("00");
                toCalStack.push("00");
            }
        });

        btnOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myInput("1");
                toCalStack.push("1");
            }
        });

        btnTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myInput("2");
                toCalStack.push("2");
            }
        });

        btnThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myInput("3");
                toCalStack.push("3");
            }
        });

        btnFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myInput("4");
                toCalStack.push("4");
            }
        });

        btnFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myInput("5");
                toCalStack.push("5");
            }
        });

        btnSix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myInput("6");
                toCalStack.push("6");
            }
        });

        btnSeven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myInput("7");
                toCalStack.push("7");
            }
        });

        btnEight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myInput("8");
                toCalStack.push("8");
            }
        });

        btnNine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myInput("9");
                toCalStack.push("9");
            }
        });

        btnDec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myInput(".");
                toCalStack.push(".");
            }
        });

        btnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quesDisplay.setText("");
                toCalculate = "";
                ansDisplay.setText("");
                quesStack.clear();
                toCalStack.clear();
            }
        });

        btnPercent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myInput("%");
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!quesDisplay.getText().toString().equals("")) {
                    ansDisplay.setText("");
                    try {
                        int N = quesDisplay.length();
                        int H = toCalculate.length();
                        int G = quesStack.lastElement().length();
                        int M = toCalStack.lastElement().length();
                        Log.d("before", toCalculate);
                        quesDisplay.setText(removeLastOccurrence(quesDisplay.getText().toString(), quesStack.lastElement(), N, G));
                        toCalculate = removeLastOccurrence(toCalculate, toCalStack.lastElement(), H, M);
                        toCalStack.pop();
                        quesStack.pop();
                        Log.d("after", toCalculate);
                        if(toCalStack.lastElement().equals("))")){
                        isBracketOpen=true;
                        bracketCount=bracketCount+1;
                        }
                    } catch (Exception e) {
                        Log.i("e", e.toString());
                    }
                } else {
                    quesDisplay.setText("");
                    toCalculate = "";
                    ansDisplay.setText("");
                }
            }
        });

        btnDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myInput("÷");
            }
        });

        btnMulti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myInput("×");
            }
        });

        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myInput("-");
            }
        });

        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myInput("+");
            }
        });

        btnEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result();
            }
        });
    }

    @SuppressLint("ClickableViewAccessibility")
    public void toShowSciPanel() {
        slide.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        y1 = event.getY();
                        break;
                    case MotionEvent.ACTION_UP:
                        y2 = event.getY();
                        if ((y1 - y2) < -200) {
                            sciPanel.setVisibility(View.VISIBLE);
                            minMode();
                        } else {
                            sciPanel.setVisibility(View.GONE);
                            maxMode();
                        }
                        break;
                }
                return true;
            }
        });
    }

    public void minMode() {
        try {
            for (int i = 0; i <= 18; i++) {
                TextView tempText = findViewById(ids[i]);
                tempText.setScaleX(0.7f);
                tempText.setScaleY(0.7f);
            }
            btnBack.setScaleX(0.7f);
            btnBack.setScaleY(0.7f);
        } catch (Exception e) {
            Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
        }
    }

    public void maxMode() {
        try {
            for (int i = 0; i <= 18; i++) {
                TextView tempText = findViewById(ids[i]);
                tempText.setScaleX(1.0f);
                tempText.setScaleY(1.0f);
            }
            btnBack.setScaleX(1.0f);
            btnBack.setScaleY(1.0f);
        } catch (Exception e) {
            Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
        }
    }

    @SuppressLint("SetTextI18n")
    public void myInput(final String num) {
        if (quesDisplay.getText().toString().equals("")) {
            switch (num) {
                case "log(":
                    toCalculate = "log10(";
                    toCalStack.push("log10(");
                    break;
                case "e":
                    if (enabledE) {
                        toCalculate = "e^";
                        toCalStack.push("e^");
                    } else {
                        toCalculate = "e";
                        toCalStack.push("e");
                    }
                    break;
                case "sin(":
                    if (!isRadian) {
                        toCalculate = "sin(rad(";
                        toCalStack.push("sin(rad(");
                        isBracketOpen = true;
                        bracketCount = bracketCount + 1;
                    } else {
                        toCalculate = "sin(";
                        toCalStack.push("sin(");
                    }
                    break;
                case "sin\u207B\u00B9(":
                    if (!isRadian) {
                        toCalculate = "deg(asin(";
                        toCalStack.push("deg(asin(");
                        isBracketOpen = true;
                        bracketCount = bracketCount + 1;
                    } else {
                        toCalculate = "asin(";
                        toCalStack.push("asin(");
                    }
                    break;
                case "cos(":
                    if (!isRadian) {
                        toCalculate = "cos(rad(";
                        toCalStack.push("cos(rad(");
                        isBracketOpen = true;
                        bracketCount = bracketCount + 1;
                    } else {
                        toCalculate = "cos(";
                        toCalStack.push("cos(");
                    }
                    break;
                case "cos\u207B\u00B9(":
                    if (!isRadian) {
                        toCalculate = "deg(acos(";
                        toCalStack.push("deg(acos(");
                        isBracketOpen = true;
                        bracketCount = bracketCount + 1;
                    } else {
                        toCalculate = "acos(";
                        toCalStack.push("acos(");
                    }
                    break;
                case "tan(":
                    if (!isRadian) {
                        toCalculate = "tan(rad(";
                        toCalStack.push("tan(rad(");
                        isBracketOpen = true;
                        bracketCount = bracketCount + 1;
                    } else {
                        toCalculate = "tan(";
                        toCalStack.push("tan(rad(");
                    }
                    break;
                case "tan\u207B\u00B9(":
                    if (!isRadian) {
                        toCalculate = "deg(atan(";
                        toCalStack.push("deg(atan(");
                        isBracketOpen = true;
                        bracketCount = bracketCount + 1;
                    } else {
                        toCalculate = "atan(";
                        toCalStack.push("atan(");
                    }
                    break;
                default:
                    toCalculate = num;
                    break;
            }
            quesDisplay.setText(num);
            quesStack.push(num);
        } else {
            if (TextUtils.isDigitsOnly(num) && enabledE) {
                switch (num) {
                    case "0":
                    case "00":
                        quesDisplay.setText(quesDisplay.getText().toString().concat("\u2070"));
                        quesStack.push("\u2070");
                        break;
                    case "1":
                        quesDisplay.setText(quesDisplay.getText().toString().concat("\u00B9"));
                        quesStack.push("\u00B9");
                        break;
                    case "2":
                        quesDisplay.setText(quesDisplay.getText().toString().concat("\u00B2"));
                        quesStack.push("\u00B2");
                        break;
                    case "3":
                        quesDisplay.setText(quesDisplay.getText().toString().concat("\u00B3"));
                        quesStack.push("\u00B3");
                        break;
                    case "4":
                        quesDisplay.setText(quesDisplay.getText().toString().concat("\u2074"));
                        quesStack.push("\u2074");
                        break;
                    case "5":
                        quesDisplay.setText(quesDisplay.getText().toString().concat("\u2075"));
                        quesStack.push("\u2075");
                        break;
                    case "6":
                        quesDisplay.setText(quesDisplay.getText().toString().concat("\u2076"));
                        quesStack.push("\u2076");
                        break;
                    case "7":
                        quesDisplay.setText(quesDisplay.getText().toString().concat("\u2077"));
                        quesStack.push("\u2077");
                        break;
                    case "8":
                        quesDisplay.setText(quesDisplay.getText().toString().concat("\u2078"));
                        quesStack.push("\u2078");
                        break;
                    case "9":
                        quesDisplay.setText(quesDisplay.getText().toString().concat("\u2079"));
                        quesStack.push("\u2079");
                        break;
                }
                toCalculate = toCalculate.concat(num);
            } else {
                switch (num) {
                    case "+":
                        toCalculate = toCalculate.concat("+");
                        toCalStack.push("+");
                        enabledE = false;
                        break;
                    case "-":
                        toCalculate = toCalculate.concat("-");
                        toCalStack.push("-");
                        enabledE = false;
                        break;
                    case "%":
                        toCalculate = toCalculate.concat("/100*");
                        toCalStack.push("/100*");
                        enabledE = false;
                        break;
                    case "÷":
                        toCalculate = toCalculate.concat("/");
                        toCalStack.push("/");
                        enabledE = false;
                        break;
                    case "×":
                        toCalculate = toCalculate.concat("*");
                        toCalStack.push("*");
                        enabledE = false;
                        break;
                    case "log(":
                        toCalculate = toCalculate.concat("log10(");
                        toCalStack.push("log10(");
                        enabledE = false;
                        break;
                    case "sin(":
                        if (!isRadian) {
                            toCalculate = toCalculate.concat("sin(rad(");
                            toCalStack.push("sin(rad(");
                            isBracketOpen = true;
                            bracketCount = bracketCount + 1;
                        } else {
                            toCalculate = toCalculate.concat("sin(");
                            toCalStack.push("sin(");
                        }
                        break;
                    case "sin\u207B\u00B9(":
                        if (!isRadian) {
                            toCalculate = "deg(sin^-1(";
                            toCalStack.push("deg(sin^-1(");
                            isBracketOpen = true;
                            bracketCount = bracketCount + 1;
                        } else {
                            toCalculate = toCalculate.concat("sin^-1(");
                            toCalStack.push("sin^-1(");
                        }
                        break;
                    case "cos(":
                        if (!isRadian) {
                            toCalculate = toCalculate.concat("cos(rad(");
                            toCalStack.push("cos(rad(");
                            isBracketOpen = true;
                            bracketCount = bracketCount + 1;
                        } else {
                            toCalculate = toCalculate.concat("cos(");
                            toCalStack.push("cos(");
                        }
                        break;
                    case "cos\u207B\u00B9(":
                        if (!isRadian) {
                            toCalculate = "deg(cos^-1(";
                            toCalStack.push("deg(cos^-1(");
                            isBracketOpen = true;
                            bracketCount = bracketCount + 1;
                        } else {
                            toCalculate = toCalculate.concat("cos^-1(");
                            toCalStack.push("cos^-1(");
                        }
                        break;
                    case "tan":
                        if (!isRadian) {
                            toCalculate = toCalculate.concat("tan(rad(");
                            toCalStack.push("tan(rad(");
                            isBracketOpen = true;
                            bracketCount = bracketCount + 1;
                        } else {
                            toCalculate = toCalculate.concat("tan(");
                            toCalStack.push("tan(");
                        }
                        break;
                    case "tan\u207B\u00B9(":
                        if (!isRadian) {
                            toCalculate = "deg(tan^-1(";
                            toCalStack.push("deg(tan^-1(");
                            isBracketOpen = true;
                            bracketCount = bracketCount + 1;
                        } else {
                            toCalculate = toCalculate.concat("tan^-1(");
                            toCalStack.push("tan^-1(");
                        }
                        break;
                    case "e":
                        if (enabledE) {
                            toCalculate = toCalculate.concat("e^");
                            toCalStack.push("e^");
                        } else {
                            toCalculate = toCalculate.concat("e");
                            toCalStack.push("e");
                        }
                        break;
                    case ")":
                        if (bracketCount >= 1) {
                            if (isBracketOpen) {
                                toCalculate = toCalculate.concat("))");
                                toCalStack.push("))");
                                bracketCount = bracketCount - 1;
                                if (bracketCount == 1) isBracketOpen = false;
                            } else {
                                toCalculate = toCalculate.concat(")");
                                toCalStack.push(")");
                            }
                        } else {
                            toCalculate = toCalculate.concat(")");
                            toCalStack.push(")");
                        }
                        break;
                    default:
                        toCalculate = toCalculate.concat(num);
                        break;
                }
                quesDisplay.setText(quesDisplay.getText().toString().concat(num));
                quesStack.push(num);
            }
        }
        try {
            result();
        } catch (Exception e) {
            ansDisplay.setText("Error");
        }
    }

    @SuppressLint("SetTextI18n")
    public void result() {
        if (!quesDisplay.getText().equals("")) {
            ansDisplay.setText("");
            try {

                Expression e = new Expression(toCalculate);
                String result = String.valueOf(e.calculate());
                Log.i("toCalculate", toCalculate);
                if (result.endsWith(".0")) {
                    result = result.substring(0, result.length() - 2);
                }

                if (result == "NaN") {
                    ansDisplay.setText("Error");
                } else {
                    ansDisplay.setText(result);
                }
                if (toCalculate == "") {
                    quesDisplay.setText("");
                    ansDisplay.setText("");
                }

            } catch (Exception ex) {
                ansDisplay.setText("Error");

            }
        }
    }

    public String removeLastOccurrence(String S, String W, int N, int M) {
        char[] ch = S.toCharArray();
        if (M > N) return S;
        for (int i = N - M; i >= 0; i--) {
            int flag = 0;
            for (int j = 0; j < M; j++) {
                if (ch[j + i] != W.charAt(j)) {
                    flag = 1;
                    break;
                }
            }
            if (flag == 0) {
                for (int j = i; j < N - M; j++)
                    ch[j] = ch[j + M];
                break;
            }
        }
        char[] chh = new char[N - M];
        if (N - M >= 0) System.arraycopy(ch, 0, chh, 0, N - M);
        return String.valueOf(chh);
    }

}
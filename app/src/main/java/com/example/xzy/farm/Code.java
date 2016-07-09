package com.example.xzy.farm;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;



import java.util.Random;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Bitmap.Config;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;


public class Code extends Activity {
        private  static final char[] CHARS = {
                '2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'j', 'k', 'l', 'm',
                'n', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
                'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
        };
        private static Code bmpCode;
        private ImageView image;
        private Button button;
        public static Code getInstance() {
            if (bmpCode == null)
                bmpCode = new Code();
            return bmpCode;
        }

        private static final int DEFAULT_CODE_LENGTH = 3;
        private static final int DEFAULT_FONT_SIZE = 25;
        private static final int DEFAULT_LINE_NUMBER = 2;
        private static final int BASE_PADDING_LEFT = 5, RANGE_PADDING_LEFT = 15, BASE_PADDING_TOP = 15, RANGE_PADDING_TOP = 20;
        private static final int DEFAULT_WIDTH = 60, DEFAULT_HEIGHT = 40;
        private int width = DEFAULT_WIDTH, height = DEFAULT_HEIGHT;
        private int codeLength = DEFAULT_CODE_LENGTH, line_number = DEFAULT_LINE_NUMBER, font_size = DEFAULT_FONT_SIZE;
        private int base_padding_left = BASE_PADDING_LEFT, range_padding_left = RANGE_PADDING_LEFT,
                base_padding_top = BASE_PADDING_TOP, range_padding_top = RANGE_PADDING_TOP;
        private String code;
        private int padding_left, padding_top;
        private Random random = new Random();

        public Bitmap createBitmap() {
            padding_left = 0;

            Bitmap bp = Bitmap.createBitmap(width, height, Config.ARGB_8888);
            Canvas c = new Canvas(bp);

            code = createCode();

            c.drawColor(Color.WHITE);
            Paint paint = new Paint();
            paint.setTextSize(font_size);

            for (int i = 0; i < code.length(); i++) {
                randomTextStyle(paint);
                randomPadding();
                c.drawText(code.charAt(i) + "", padding_left, padding_top, paint);
            }

            for (int i = 0; i < line_number; i++) {
                drawLine(c, paint);
            }

            c.save(Canvas.ALL_SAVE_FLAG);//保存
            c.restore();//
            return bp;
        }

        public String getCode() {
            return code;
        }

        private String createCode() {
            StringBuilder buffer = new StringBuilder();
            for (int i = 0; i < codeLength; i++) {
                buffer.append(CHARS[random.nextInt(CHARS.length)]);
            }
            return buffer.toString();
        }

        private void drawLine(Canvas canvas, Paint paint) {
            int color = randomColor();
            int startX = random.nextInt(width);
            int startY = random.nextInt(height);
            int stopX = random.nextInt(width);
            int stopY = random.nextInt(height);
            paint.setStrokeWidth(1);
            paint.setColor(color);
            canvas.drawLine(startX, startY, stopX, stopY, paint);
        }

        private int randomColor() {
            return randomColor(1);
        }

        private int randomColor(int rate) {
            int red = random.nextInt(256) / rate;
            int green = random.nextInt(256) / rate;
            int blue = random.nextInt(256) / rate;
            return Color.rgb(red, green, blue);
        }

        private void randomTextStyle(Paint paint) {
            int color = randomColor();
            paint.setColor(color);
            paint.setFakeBoldText(random.nextBoolean());
            float skewX = random.nextInt(11) / 10;
            skewX = random.nextBoolean() ? skewX : -skewX;
            paint.setTextSkewX(skewX);
        }

        private void randomPadding() {
            padding_left += base_padding_left + random.nextInt(range_padding_left);
            padding_top = base_padding_top + random.nextInt(range_padding_top);
        }


    private String[] kind1 = new String[]{"鸡","鸭","鹅"};
    private String[][] kind2 = new String[][]{
            {"乌鸡","芦花鸡","柴鸡","贵妃鸡","三黄鸡","杏花鸡"},
            {"北京鸭","绍鸭","高邮鸭","金定鸭","樱桃谷鸭","狄高鸭"},
            {"四川白鹅","马岗鹅","狮头鹅"},
    };

    Spinner kind1Spinner = null;
    Spinner kind2Spinner = null;
    ArrayAdapter<String> kind1Adapter = null;
    ArrayAdapter<String> kind2Adapter = null;

       @Override
    protected void onCreate(Bundle savedInstanceState){
           super.onCreate(savedInstanceState);
           requestWindowFeature(Window.FEATURE_NO_TITLE);
           setContentView(R.layout.code);

           Button button_sign_up = (Button) findViewById(R.id.sign_up);
           Button button_sign_up_cancel = (Button) findViewById(R.id.sign_up_cancel);

           button_sign_up.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   Intent intent = new Intent(Code.this, LoginActivity.class);
                   startActivity(intent);
               }
           });

           button_sign_up_cancel.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   Intent intent = new Intent(Code.this, LoginActivity.class);
                   startActivity(intent);
               }
           });

//           requestWindowFeature(Window.FEATURE_NO_TITLE);
           image = (ImageView)findViewById(R.id.image);
           button = (Button)findViewById(R.id.button1);
           image.setImageBitmap(getInstance().createBitmap());
           button.setOnClickListener(new View.OnClickListener(){
                 public void onClick(View v){
                     image.setImageBitmap(getInstance().createBitmap());
                 }
           });

           kind1Spinner = (Spinner) findViewById(R.id.spin_kind_1);
           kind2Spinner = (Spinner) findViewById(R.id.spin_kind_2);
           kind1Adapter = new ArrayAdapter<String>(Code.this,android.R.layout.simple_spinner_item,kind1);
           kind1Spinner.setAdapter(kind1Adapter);
           kind1Spinner.setSelection(0,true);
           kind2Adapter = new ArrayAdapter<String>(Code.this,android.R.layout.simple_spinner_item,kind2[0]);
           kind2Spinner.setAdapter(kind2Adapter);
           kind2Spinner.setSelection(0,true);

           kind1Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
               @Override
               public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                   kind2Adapter = new ArrayAdapter<String>(Code.this,android.R.layout.simple_spinner_item,kind2[position]);
                   kind2Spinner.setAdapter(kind2Adapter);
               }

               @Override
               public void onNothingSelected(AdapterView<?> parent) {

               }
           });
       }


}




package com.myapplication.View;

/**
 * Created by www86 on 2020-07-21.
 */

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;


import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.myapplication.Model.HtmlContent;
import com.myapplication.R;
import com.myapplication.UtilsHelper.Dealstring;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

import jp.wasabeef.richeditor.RichEditor;

import static android.app.Activity.RESULT_OK;
import static android.content.ContentValues.TAG;


public class Fragment4 extends Fragment {
    private View view;
    private RichEditor mEditor;
    private TextView mPreview;
    private final String IMAGE_TYPE = "image/*";
    private HtmlContent htmlContent = new HtmlContent();
    private final int IMAGE_CODE = 0;   //这里的IMAGE_CODE是自己任意定义的
    public String imgpath=null;
    private Button addPic=null,showPicPath=null;
    List<String> realurls = new ArrayList<>();
    public Fragment4() {
        // Required empty public constructor
    }

    String[] mPermissionList = new String[]{
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE};

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_fragment4, container, false);
        return view;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mEditor = (RichEditor) getActivity().findViewById(R.id.editor);
        mEditor.setEditorHeight(200);
        mEditor.setEditorFontSize(22);
        mEditor.setEditorFontColor(Color.RED);
        //mEditor.setEditorBackgroundColor(Color.BLUE);
        //mEditor.setBackgroundColor(Color.BLUE);
        //mEditor.setBackgroundResource(R.drawable.bg);
        mEditor.setPadding(10, 10, 10, 10);
        //    mEditor.setBackground("https://raw.githubusercontent.com/wasabeef/art/master/chip.jpg");
        mEditor.setPlaceholder("Insert text here...");

        mPreview = (TextView) getActivity().findViewById(R.id.preview);
        mEditor.setOnTextChangeListener(new RichEditor.OnTextChangeListener() {
            @Override public void onTextChange(String text) {
                mPreview.setText(text);
            }
        });

        getActivity().findViewById(R.id.btn_submit).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                //实现html及图片组上传
                try {
               String content = mPreview.getText().toString();
                int index = 0;
                ContentResolver cr = getActivity().getContentResolver();
                SharedPreferences sp = getActivity().getSharedPreferences("Login", Context.MODE_PRIVATE);
                String to = sp.getString("token",null);
                    Fragment4.MyCallable mc = new Fragment4.MyCallable(content, realurls,to);
                    FutureTask<String> ft = new FutureTask<>(mc);
                    Thread thread = new Thread(ft);
                    thread.start();
                    String res = ft.get();
                if (res.equals("true")){
                    Log.i("wangss", "success");
                }else {
                    Log.i("wangss", "fail");
                }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        getActivity().findViewById(R.id.action_undo).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.undo();
            }        });

        getActivity(). findViewById(R.id.action_redo).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.redo();
            }
        });

        getActivity().findViewById(R.id.action_bold).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.setBold();
            }
        });

        getActivity().findViewById(R.id.action_italic).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.setItalic();
            }
        });

        getActivity().findViewById(R.id.action_subscript).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.setSubscript();
            }
        });

        getActivity().findViewById(R.id.action_superscript).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.setSuperscript();
            }
        });

        getActivity().findViewById(R.id.action_strikethrough).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.setStrikeThrough();
            }
        });

        getActivity().findViewById(R.id.action_underline).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.setUnderline();
            }
        });

        getActivity().findViewById(R.id.action_heading1).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.setHeading(1);
            }
        });

        getActivity().findViewById(R.id.action_heading2).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.setHeading(2);
            }
        });

        getActivity().findViewById(R.id.action_heading3).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.setHeading(3);
            }
        });

        getActivity().findViewById(R.id.action_heading4).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.setHeading(4);
            }
        });

        getActivity().findViewById(R.id.action_heading5).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.setHeading(5);
            }
        });

        getActivity().findViewById(R.id.action_heading6).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.setHeading(6);
            }
        });

        getActivity().findViewById(R.id.action_txt_color).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.focusEditor();
                new MaterialDialog.Builder(getActivity())
                        .title("选择字体颜色")
                        .items(new String[]{"红色","黄色","绿色","蓝色","黑色"})
                        .itemsCallbackSingleChoice(-1, new MaterialDialog.ListCallbackSingleChoice() {
                            @Override
                            public boolean onSelection(MaterialDialog dialog, View itemView, int which, CharSequence text) {
                                dialog.dismiss();
                                switch (which) {
                                    case 0://红
                                        mEditor.setTextColor(Color.RED);
                                        break;
                                    case 1://黄
                                        mEditor.setTextColor(Color.YELLOW);
                                        break;
                                    case 2://绿
                                        mEditor.setTextColor(Color.GREEN);
                                        break;
                                    case 3://蓝
                                        mEditor.setTextColor(Color.BLUE);
                                        break;
                                    case 4://黑
                                        mEditor.setTextColor(Color.BLACK);
                                        break;
                                }
                                return false;
                            }
                        }).show();

            }
        });

        getActivity().findViewById(R.id.action_bg_color).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.focusEditor();
                new MaterialDialog.Builder(getActivity())
                        .title("选择字体背景颜色")
                        .items(new String[]{"红色","黄色","绿色","蓝色","黑色","透明"})
                        .itemsCallbackSingleChoice(-1, new MaterialDialog.ListCallbackSingleChoice() {
                            @Override
                            public boolean onSelection(MaterialDialog dialog, View itemView, int which, CharSequence text) {
                                dialog.dismiss();
                                switch (which) {
                                    case 0://红
                                        mEditor.setTextBackgroundColor(Color.RED);
                                        break;
                                    case 1://黄
                                        mEditor.setTextBackgroundColor(Color.YELLOW);
                                        break;
                                    case 2://绿
                                        mEditor.setTextBackgroundColor(Color.GREEN);
                                        break;
                                    case 3://蓝
                                        mEditor.setTextBackgroundColor(Color.BLUE);
                                        break;
                                    case 4://黑
                                        mEditor.setTextBackgroundColor(Color.BLACK);
                                        break;
                                    case 5://透明
                                        mEditor.setTextBackgroundColor(R.color.transparent);
                                        break;
                                }
                                return false;
                            }
                        }).show();
            }
        });

        getActivity().findViewById(R.id.action_indent).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.setIndent();
            }
        });

        getActivity().findViewById(R.id.action_outdent).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.setOutdent();
            }
        });

        getActivity().findViewById(R.id.action_align_left).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.setAlignLeft();
            }
        });

        getActivity().findViewById(R.id.action_align_center).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.setAlignCenter();
            }
        });

        getActivity().findViewById(R.id.action_align_right).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.setAlignRight();
            }
        });
        getActivity().findViewById(R.id.action_insert_bullets).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.setBullets();
            }
        });
        getActivity().findViewById(R.id.action_insert_numbers).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.setNumbers();
            }
        });

        getActivity().findViewById(R.id.action_blockquote).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.setBlockquote();
            }
        });

        getActivity().findViewById(R.id.action_insert_image).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.focusEditor();
                setImage();
            }
        });

        getActivity().findViewById(R.id.action_insert_link).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.insertLink("https://github.com/wasabeef", "测试，可有可无");
            }
        });
        getActivity().findViewById(R.id.action_insert_checkbox).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {

                mEditor.insertTodo();
            }
        });

    }

    //获取图片
    private void setImage() {
        // TODO Auto-generated method stub
        //使用intent调用系统提供的相册功能，使用startActivityForResult是为了获取用户选择的图片
        Intent getAlbum = new Intent(Intent.ACTION_GET_CONTENT);
        getAlbum.setType(IMAGE_TYPE);
        startActivityForResult(getAlbum, IMAGE_CODE);
    }

    //重写onActivityResult以获得图片路径
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if (resultCode != RESULT_OK) {        //此处的 RESULT_OK 是系统自定义得一个常量
            Log.e(TAG,"ActivityResult resultCode error");
            return;
        }
        if (requestCode == IMAGE_CODE && data!=null) {
                Uri originalUri = data.getData();        //获得图片的uri
                imgpath = originalUri.toString();
                realurls.add(getRealPathFromURI(originalUri));
                mEditor.insertImage(imgpath, "dachshund",200);
                imgpath = null;
        }

    }
    //Callable 有返回值的多线程账号密码
    public class MyCallable implements Callable<String> {
        private String content;
        private List<String> realurls;
        private String to;
        public MyCallable(String content, List<String> realurls, String to){
            this.content = content;
            this.realurls = realurls;
            this.to = to;
        }
        @Override
        public String call() {
            String jso = null;
            try {
            jso =   htmlContent.htmlcontent(content,realurls,to);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return jso;
        }
    }
    private String getRealPathFromURI(Uri contentURI) {
        String result;
            result = contentURI.getPath();
        return result;
    }
};







package com.sumalogos.webster.sumalogos.util;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sumalogos.webster.sumalogos.R;
import com.sumalogos.webster.sumalogos.activity.HomeActivity;
import com.sumalogos.webster.sumalogos.model.Devotion;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by webster on 09/01/18.
 */

public class DevotionAdapter extends RecyclerView.Adapter<DevotionAdapter.ViewHolder> {

    Spanned spanned;

    private Context context;

    private List<Devotion> devotions;

    private HomeActivity.BtnSelesaiOnClickListener listener;

    private AppDatabase db;

//    private String[] oldTestamentBooks = AppConstant.oldTestamentBooks;
//
//    private String[] newYestamentBooks = AppConstant.newTestamentBooks;

    private Intent intent1;
    private Intent intent2;
    private Intent intent3;
    private Intent intent4;

    private String[] arrayMonth = AppConstant.arrayMonthIndonesiaSentenceCase;

    @Override
    public int getItemCount() {
        return devotions.size();
    }

    public DevotionAdapter(List<Devotion> devotions, Context context,
                           HomeActivity.BtnSelesaiOnClickListener listener) {
        this.devotions = devotions;
        this.context = context;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        LayoutInflater mInflater = LayoutInflater.from(viewGroup.getContext());

        ViewGroup mainGroup = (ViewGroup) mInflater.inflate(
                R.layout.item_row, viewGroup, false);

        db = AppDatabase.getInstance();

        return new ViewHolder(mainGroup);

    }

//    private String mappingBooks(String book) {
//        String result = "";
////Kejadian 2-5
//        if (book.contains("#")) {
//            book = book.split("#")[0].trim();
//        }
//
//        if (book.contains(" ")) {
//            book = book.split(" ")[0];
//        }
//
//        book = book.trim();
//
//        switch (book.toLowerCase()) {
//            case "kejadian": result = "Gen"; break;
//            case "keluaran": result = "Exod"; break;
//            case "imamat": result = "Lev"; break;
//            case "bilangan": result = "Num"; break;
//            case "ulangan": result = "Deut"; break;
//            case "yosua": result = "Josh"; break;
//            case "hakim-hakim": result = "Judg"; break;
//            case "rut": result = "Ruth"; break;
//            case "1 samuel": result = "1Sam"; break;
//            case "2 samuel": result = "2Sam"; break;
//            case "1 raja-raja": result = "1Kgs"; break;
//            case "2 raja-raja": result = "2Kgs"; break;
//            case "1 tawarikh": result = "1Chr"; break;
//            case "2 tawarikh": result = "2Chr"; break;
//            case "ezra": result = "Ezra"; break;
//            case "nehemia": result = "Neh"; break;
//            case "ester": result = "Esth"; break;
//            case "ayub": result = "Job"; break;
//            case "mazmur": result = "Ps"; break;
//            case "amsal": result = "Prov"; break;
//            case "pengkhotbah": result = "Eccl"; break;
//            case "kidung agung": result = "Song"; break;
//            case "yesaya": result = "Isa"; break;
//            case "yeremia": result = "Jer"; break;
//            case "ratapan": result = "Lam"; break;
//            case "yehezkiel": result = "Ezek"; break;
//            case "daniel": result = "Dan"; break;
//            case "hosea": result = "Hos"; break;
//            case "yoel": result = "Joel"; break;
//            case "amos": result = "Amos"; break;
//            case "obaja": result = "Obad"; break;
//            case "yunus": result = "Jonah"; break;
//            case "mikha": result = "Mic"; break;
//            case "nahum": result = "Nah"; break;
//            case "habakuk": result = "Hab"; break;
//            case "zefanya": result = "Zeph"; break;
//            case "hagai": result = "Hag"; break;
//            case "zakharia": result = "Zech"; break;
//            case "maleakhi": result = "Mal"; break;
//
//            case "matius": result = "Matt"; break;
//            case "markus": result = "Mark"; break;
//            case "lukas": result = "Luke"; break;
//            case "yohanes": result = "John"; break;
//            case "kisah para rasul": result = "Acts"; break;
//            case "roma": result = "Rom"; break;
//            case "1 korintus": result = "1Cor"; break;
//            case "2 korintus": result = "2Cor"; break;
//            case "galatia": result = "Gal"; break;
//            case "efesus": result = "Eph"; break;
//            case "filipi": result = "Phil"; break;
//            case "kolose": result = "Col"; break;
//            case "1 tesalonika": result = "1Thess"; break;
//            case "2 tesalonika": result = "2Thess"; break;
//            case "1 timotius": result = "1Tim"; break;
//            case "2 timotius": result = "2Tim"; break;
//            case "titus": result = "Titus"; break;
//            case "filemon": result = "Phlm"; break;
//            case "ibrani": result = "Heb"; break;
//            case "yakobus": result = "Jas"; break;
//            case "1 petrus": result = "1Pet"; break;
//            case "2 petrus": result = "2Pet"; break;
//            case "1 yohanes": result = "1John"; break;
//            case "2 yohanes": result = "2John"; break;
//            case "3 yohanes": result = "3John"; break;
//            case "yudas": result = "Jude"; break;
//            case "wahyu": result = "Rev"; break;
//
//        }
//
//        return result;
//
//    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final Devotion devotion = devotions.get(position);

        try {

            if (intent1 == null) {
                intent1 = context.getPackageManager().getLaunchIntentForPackage("yuku.alkitab");
                intent2 = context.getPackageManager().getLaunchIntentForPackage("org.sabda.alkitab");
                intent3 = context.getPackageManager().getLaunchIntentForPackage("com.gulbers.alkitabkidung");
                intent4 = context.getPackageManager().getLaunchIntentForPackage("com.alkitabku.android");
            }

            String strDate = devotion.getDate().split("T")[0];

            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(strDate);

            int day = devotion.getDay();

            int month = date.getMonth();

            String year = strDate.split("-")[0];

            String todayDate = day + " " + arrayMonth[month] + " " + year;
            String book = devotion.getBook().replace("#","<br>");
            spanned = Html.fromHtml("<a href='#'>" + book + "</a>");

            holder.tvVerse.setText(spanned);
            holder.tvDays.setText(devotion.getWeekDay());
            holder.tvDates.setText(todayDate);

            holder.tvVerse.setOnClickListener(view -> {

                try {
                    if (intent1 != null) {
                        intent1 = new Intent("yuku.alkitab.action.SHOW_VERSES_DIALOG");
                        intent1.putExtra("target", "o:" + devotion.getBookParam());
                        intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT);
                        context.startActivity(intent1);
                    } else if (intent2 != null) {
                        context.startActivity(intent2);
                    } else if (intent3 != null) {
                        context.startActivity(intent3);
                    } else if (intent4 != null) {
                        context.startActivity(intent4);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(context,
                            "Ada masalah dalam membuka Alkitab",
                            Toast.LENGTH_SHORT).show();
                }

            });

            initShareOnClick(holder, devotion, todayDate);

            String totalRead = devotion.getTotalRead() + "";

            holder.tvTotalRead.setText(totalRead);

            holder.tvVerse.setMovementMethod(LinkMovementMethod.getInstance());

            if (devotion.isFinished()) {

                holder.btnSelesai.setText("");

                holder.btnSelesai.setBackground(context.getResources()
                        .getDrawable(R.drawable.ic_check_mark));

                holder.btnSelesai.setEnabled(false);
            } else {

                holder.btnSelesai.setBackground(context.getResources()
                        .getDrawable(R.drawable.rounded_button_finish));

                holder.btnSelesai.setText("Selesai");

                holder.btnSelesai.setEnabled(true);
            }

            holder.btnSelesai.setOnClickListener(view -> {
                if (listener != null) {

                    devotion.setTotalRead(devotion.getTotalRead() + 1);
                    devotion.setFinished(true);
                    String totalRead1 = devotion.getTotalRead() + "";

                    db.devotionDAO().updateDevotion(devotion);

                    Button btn = (Button) view;
                    btn.setEnabled(true);

                    btn.setText("");

                    btn.setBackground(context.getResources()
                            .getDrawable(R.drawable.ic_check_mark));

                    holder.tvTotalRead.setText(totalRead1);
                    devotions.set(position, devotion);
                    notifyItemChanged(position);

                    listener.onClick("Finished reading - " + devotion.getBook(),
                            devotion.getId(), view, position);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initShareOnClick(ViewHolder holder, Devotion devotion, String todayDate) {
        holder.ivShare.setOnClickListener(view -> {
            try {
                Intent share = new Intent(android.content.Intent.ACTION_SEND);
                share.setType("text/plain");
                share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);

                share.putExtra(Intent.EXTRA_SUBJECT, "Summa Logos - " + todayDate + " - " +
                devotion.getBook().replace("#", ","));
                share.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=com.sumalogos.webster.sumalogos");

                context.startActivity(Intent.createChooser(share, "Bagikan!"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvVerse)
        TextView tvVerse;

        @BindView(R.id.tvDays)
        TextView tvDays;

        @BindView(R.id.tvDates)
        TextView tvDates;

        @BindView(R.id.card_view)
        CardView cardView;

        @BindView(R.id.btnSelesai)
        Button btnSelesai;

        @BindView(R.id.tvTotalRead)
        TextView tvTotalRead;

        @BindView(R.id.ivShare)
        ImageView ivShare;

        private ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }


}

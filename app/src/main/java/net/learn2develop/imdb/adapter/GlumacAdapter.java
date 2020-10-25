package net.learn2develop.imdb.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import net.learn2develop.imdb.model.Glumac;
import net.learn2develop.imdb.R;

import java.util.List;

    public class GlumacAdapter extends BaseAdapter {
        private List<Glumac> glumac;
        private Activity activity;

        public GlumacAdapter(List<Glumac> glumac, Activity activity) {
            this.glumac = glumac;
            this.activity = activity;
        }

        @Override
        public int getCount() {
            return glumac.size();
        }

        @Override
        public Object getItem(int position) {
            return glumac.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = activity.getLayoutInflater().inflate(R.layout.naziv_glumca, null);
            }
            TextView tvNaziv = view.findViewById(R.id.textView_NazivGlumca);
            tvNaziv.setText(glumac.get(position).getIme());
            return view;

        }
    }



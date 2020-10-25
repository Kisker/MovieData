package net.learn2develop.imdb.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import net.learn2develop.imdb.R;
import net.learn2develop.imdb.model.Glumac;
import net.learn2develop.imdb.adapter.GlumacAdapter;
import net.learn2develop.imdb.providers.GlumacProvider;

import java.util.ArrayList;
import java.util.List;

public class GlumciFragment extends Fragment {
    private ListView listView_Glumac;
    private List<Glumac> glumac = new ArrayList<>();
    private onGlumacClickListener listener;

    public GlumciFragment() {
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_lista, container, false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView_Glumac = view.findViewById(R.id.lvPodaci);

        setupList();
    }

    private void setupList() {
        glumac = GlumacProvider.getAllGlumac();

        GlumacAdapter adapter = new GlumacAdapter(glumac,getActivity());
        listView_Glumac.setAdapter(adapter);
        // pozivom na ovu metodu, odnosno na interface onItemClick unutar fijoke Glumci, nama se otvara sledeci prozor sa opisom glumaca
        listView_Glumac.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (listener != null) {
                    listener.onGlumacClicked(glumac.get(i));
                }
            }
        });
    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof onGlumacClickListener) {
            listener = (onGlumacClickListener) context;
        } else {
            Toast.makeText(getContext(), "Morate implementirati onAtach List", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    public interface onGlumacClickListener {
        void onGlumacClicked(Glumac glumac);
    }
}

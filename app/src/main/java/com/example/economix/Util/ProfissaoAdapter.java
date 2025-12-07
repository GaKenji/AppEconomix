package com.example.economix.Util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.economix.API.ProfissaoResponse;
import com.example.economix.R;

import java.util.List;

public class ProfissaoAdapter extends ArrayAdapter<ProfissaoResponse> {

    private final LayoutInflater inflater;
    public ProfissaoAdapter(@NonNull Context context, List<ProfissaoResponse> lista) {
        super(context, 0, lista);
        inflater = LayoutInflater.from(context);
    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        return criarView(position, convertView, parent);
    }
    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return criarView(position, convertView, parent);
    }

    private View criarView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        if(convertView == null){
            convertView = inflater.inflate(R.layout.spinner_profissoes_item, parent, false);
        }

        TextView nomeProfissao = convertView.findViewById(R.id.textViewProfissaoSpinnerItem);
        TextView salario = convertView.findViewById(R.id.textViewSalarioSpinnerItem);
        ProfissaoResponse profissao= getItem(position);

        if(profissao != null){
            nomeProfissao.setText(profissao.getNome());
            salario.setText("Salário: "+profissao.getSalariomensal()+" R$");
        }
        return convertView;
    }
}

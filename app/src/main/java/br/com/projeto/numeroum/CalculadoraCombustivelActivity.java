package br.com.projeto.numeroum;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CalculadoraCombustivelActivity extends AppCompatActivity {

    private EditText editGasolina, editAlcool;
    private RadioGroup radioGroupAlcool;
    private TextView textResultadoCombu;
    private Button btnCombustivel;
    public Button btnFecharAba;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculadora_combustivel);

        // botoes a serem usados na pagina
        editGasolina = findViewById(R.id.editGasolina);
        editAlcool = findViewById(R.id.editAlcool);
        radioGroupAlcool = findViewById(R.id.radioGroupAlcool);
        textResultadoCombu = findViewById(R.id.textResultadoCombu);
        btnCombustivel = findViewById(R.id.btnCombustivel);
        btnFecharAba = findViewById(R.id.btnFecharAba);

        //botão "Calcular"
        btnCombustivel.setOnClickListener(v -> calcularValor());

        btnFecharAba.setOnClickListener(v -> finish()); // Fecha a Activity ao clicar

        radioGroupAlcool.setOnCheckedChangeListener((group, checkedId) -> calcularValor());
    }

    private void calcularValor() {
        String gasolinaStr = editGasolina.getText().toString();
        String alcoolStr = editAlcool.getText().toString();

        if (gasolinaStr.isEmpty() || alcoolStr.isEmpty()) {
            textResultadoCombu.setText("Por favor, insira ambos os valores.");
            return;
        }

        double gasolina = Double.parseDouble(gasolinaStr);
        double alcool = Double.parseDouble(alcoolStr);

        double valorReferencia = gasolina * 0.7;

        String combustivelEscolhido = "Gasolina"; // Padrão
        double diferenca = 0;

        // Verificar o tipo de combustível selecionado
        int selectedId = radioGroupAlcool.getCheckedRadioButtonId();
        RadioButton radioButton = findViewById(selectedId);

        if (radioButton != null) {
            combustivelEscolhido = radioButton.getText().toString();
            if (combustivelEscolhido.equals("Álcool")) {
                diferenca = (alcool - valorReferencia) / valorReferencia * 100; // percentual
            } else {
                diferenca = (gasolina - valorReferencia) / valorReferencia * 100; // percentual
            }
        }

        String mensagem = String.format("Escolha: %s\nDiferença: %.2f%% acima de 70%%", combustivelEscolhido, diferenca);
        textResultadoCombu.setText(mensagem);
    }
}

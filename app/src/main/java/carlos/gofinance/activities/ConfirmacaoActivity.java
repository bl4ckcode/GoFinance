package carlos.gofinance.activities;

import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import carlos.gofinance.R;

public class ConfirmacaoActivity extends AppCompatActivity {
    private TextView txtViewDesc;
    private RelativeLayout rlImgContainer;
    private TextView txtViewMsg;
    private Button btnPular;
    private Button btnContinuar;
    private ImageView imViewIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmacao);

        Resources recursos = getResources();

        Bundle extras = getIntent().getExtras();

        txtViewDesc = (TextView) findViewById(R.id.txtView_desc);
        rlImgContainer = (RelativeLayout) findViewById(R.id.rl_img_container);
        txtViewMsg = (TextView) findViewById(R.id.txtView_msg);
        btnPular = (Button) findViewById(R.id.btn_pular);
        btnContinuar = (Button) findViewById(R.id.btn_continuar);
        imViewIcon = (ImageView) findViewById(R.id.imView_icon);

        if (extras.containsKey(CriarCartaoContaActivity.REQUEST_CONFIRMACAO_CONTA)) {
            rlImgContainer.getBackground().setColorFilter(recursos.getColor(R.color.toolbar_color, null), PorterDuff.Mode.SRC_ATOP);
            imViewIcon.setImageResource(R.drawable.ic_conta_big);
            txtViewMsg.setText(recursos.getString(R.string.confirmacao_activity_msg_conta));
            btnPular.setTextColor(recursos.getColor(R.color.toolbar_color, null));
            btnContinuar.getBackground().setColorFilter(recursos.getColor(R.color.toolbar_color, null), PorterDuff.Mode.SRC_ATOP);
        } else if (extras.containsKey(CriarCartaoContaActivity.REQUEST_CONFIRMACAO_CARTAO)) {
            rlImgContainer.getBackground().setColorFilter(recursos.getColor(R.color.toolbar_color, null), PorterDuff.Mode.SRC_ATOP);
            imViewIcon.setImageResource(R.drawable.ic_cartao_big);
            txtViewDesc.setText(recursos.getString(R.string.confirmacao_activity_title_cartao));
            txtViewMsg.setText(recursos.getString(R.string.confirmacao_activity_msg_cartao));
            btnPular.setTextColor(recursos.getColor(R.color.toolbar_color, null));
            btnContinuar.getBackground().setColorFilter(recursos.getColor(R.color.toolbar_color, null), PorterDuff.Mode.SRC_ATOP);
        } else if (extras.containsKey(CriarReceitaDespesaActivity.REQUEST_CONFIRMACAO_DESPESA)) {
            imViewIcon.setImageResource(R.drawable.ic_despesa_big);
            txtViewDesc.setText(recursos.getString(R.string.confirmacao_activity_title_despesa));
            txtViewMsg.setText(recursos.getString(R.string.confirmacao_activity_msg_despesa));
        } else {
            rlImgContainer.getBackground().setColorFilter(recursos.getColor(R.color.toolbar_color, null), PorterDuff.Mode.SRC_ATOP);
            imViewIcon.setImageResource(R.drawable.ic_carteira_big);
            txtViewDesc.setText(recursos.getString(R.string.confirmacao_activity_title_receita));
            txtViewMsg.setText(recursos.getString(R.string.confirmacao_activity_msg_receita));
            btnPular.setTextColor(recursos.getColor(R.color.toolbar_color, null));
            btnContinuar.getBackground().setColorFilter(recursos.getColor(R.color.toolbar_color, null), PorterDuff.Mode.SRC_ATOP);
        }


        btnPular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}

package projeto.android.primeiroappemkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import projeto.android.primeiroappemkotlin.databinding.ActivityLoginBinding
import projeto.android.primeiroappemkotlin.util.vaiPara

class LoginActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        configuraBotaoCadastrar()
        configuraBotaoEntrar()
    }

    private fun configuraBotaoEntrar() {
        binding.activityLoginBotaoEntrar.setOnClickListener {
            val usuario = binding.activityLoginUsuario.text.toString()
            val senha = binding.activityLoginSenha.text.toString()
            Log.i("LoginActivity", "onCreate: $usuario - $senha")
            vaiPara(MainActivity::class.java)
        }
    }

    private fun configuraBotaoCadastrar() {
        binding.activityLoginBotaoCadastrar.setOnClickListener {
            vaiPara(FormularioCadastroUsuarioActivity::class.java)
        }
    }
}
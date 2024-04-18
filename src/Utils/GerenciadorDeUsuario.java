package Utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Models.Usuario;

public class GerenciadorDeUsuario {

	private static final String NOME_ARQUIVO = "usuario.txt";

	// verificar a existencia do nosso banco e criar caso nao exista
	public void verificaECriar(String nomeArquivo) {
		// file => arquivo
		File arquivo = new File(nomeArquivo);
		// verificar se o arquivo existe
		if (arquivo.exists()) {
			System.out.println("Banco funcionando.");
		} else // tente criar, caso de erro, exibe o erro
			try {
				// Criar o novo arquivo
				arquivo.createNewFile();
				System.out.println("Arquivo criado...");
			} catch (IOException e) {
				System.err.println("Ocorreu um erro ao criar o arquivo" + e.getMessage());
			}
	}

	public void adicionarUsuario(Usuario usuario) {

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(NOME_ARQUIVO, true))) {

			bw.write(usuario.toString());
			bw.newLine();
			System.out.println("Usuario cadastrado com sucesso");

		} catch (IOException e) {
			System.err.println("Ocorreu um erro ao escrever no arquivo: " + e.getMessage());

		}

	}

	public List<Usuario> lerUsuarios() {
		List<Usuario> usuarios = new ArrayList<Usuario>();
		try (BufferedReader br = new BufferedReader(new FileReader(NOME_ARQUIVO))) {
			String linha;

			while ((linha = br.readLine()) != null) {
				String[] partes = linha.split(";");

				// adicionar usuario na lista-
				usuarios.add(new Usuario(Integer.parseInt(partes[0]), partes[1], partes[2]));

			}
		} catch (IOException e) {
			System.err.println("Ocorreu um erro ao ler o arquivo: " + e.getMessage());
		}
		return usuarios;
	}

	public void deletarUsuario(int id) {
		List<Usuario> usuarios = lerUsuarios();
		if (usuarios.removeIf(usuario -> usuario.getId() == id)) {
			reescreverArquivo(usuarios);
			System.out.println("Usuario deletado com sucesso");
		} else {
			System.err.println("Usuario nao encontrado");
		}

	}

	public void reescreverArquivo(List<Usuario> usuarios) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(NOME_ARQUIVO))) {
			for (Usuario usuario : usuarios) {
				bw.write(usuario.toString());
				bw.newLine();
			}

		} catch (IOException e) {
			System.err.println("Ocorreu um erro ao reescrever o arquivo");
		}
	}

	public void listarUsuarios() {
		List<Usuario> usuarios = lerUsuarios();
		if (usuarios.isEmpty()) {
			System.out.println("Nenhum usuario cadastrado");
		} else {
			System.out.println("Lista de usuarios: ");
			for (Usuario usuario : usuarios) {
				System.out.println(
						"ID:" + usuario.getId() + " Nome:" + usuario.getNome() + " senha:" + usuario.getSenha());
			}
		}

	}

	public void editarUsuarios(int id, String novoNome, String novaSenha) {
		List<Usuario> usuarios = lerUsuarios();
		boolean encontrado = false;
		for (Usuario usuario : usuarios) {
			if (usuario.getId() == id) {
				usuario.setNome(novoNome);
				usuario.setSenha(novaSenha);
				encontrado = true;
				break;
			}
		}

		if (encontrado) {
			reescreverArquivo(usuarios);
			System.out.println("Usuario editado com sucesso");
		} else {
			System.out.println("usuario nao encontrado");
		}

	}

	public void listarEspecifico(int id) {
		List<Usuario> usuarios = lerUsuarios();
		for (Usuario usuario : usuarios) {
			if (usuario.getId() == id) {
				System.out.println(
						"ID:" + usuario.getId() + " Nome:" + usuario.getNome() + " Senha:" + usuario.getSenha());

			}

		}

	}

	public void fazerLogin(String nome, String senha) {
		List<Usuario> usuarios = lerUsuarios();
		boolean existe = false;
		for (Usuario usuario : usuarios) {
			if (usuario.getNome().equals(nome) && usuario.getSenha().equals(senha)) {
				existe = true;
				System.out.println("Login feito com sucesso");
			}
		}
		
		if (existe != true) {
			System.err.println("Nome ou senha incorretos!");

		}

	}
}

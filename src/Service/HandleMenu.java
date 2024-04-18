package Service;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import Models.Usuario;
import Utils.GerenciadorDeUsuario;

public class HandleMenu {

	Scanner sc = new Scanner(System.in);

	GerenciadorDeUsuario gs = new GerenciadorDeUsuario();

	public HandleMenu() {
		gs.verificaECriar("usuario.txt");

	}

	public void criar() {
		System.out.println("Digite seu nome: ");
		String nome = sc.next();

		System.out.println("Digite a senha: ");
		String senha = sc.next();

		int id = getNextId();

		Usuario u = new Usuario(id, nome, senha);
		gs.adicionarUsuario(u);

	}

	public void editar() {
		System.out.println("Digite o id do usuario: ");
		int id = sc.nextInt();
		System.out.println("Digite um novo nome: ");
		String nome = sc.next();
		System.out.println("Digite a nova senha: ");
		String senha = sc.next();
		gs.editarUsuarios(id, nome, senha);
		
		
		

	}

	public void deletar() {
		System.out.println("Digite o Id do usuario a ser deletado: ");
		int id = sc.nextInt();
		gs.deletarUsuario(id);

	}

	public void listar() {
		gs.listarUsuarios();
	}
	
	public void listarEspefico() {
		System.out.println("Digite o id do usuario que deseja detalhar: ");
		int id = sc.nextInt();
		gs.listarEspecifico(id);
		
	}
	public void fazerLogin() {
		System.out.print("Digite seu nome: ");
		String nome = sc.next();
		System.out.print("Digite sua senha: ");
		String senha = sc.next();
		gs.fazerLogin(nome, senha);
	}

	public int getNextId() {
		List<Usuario> usuarios = gs.lerUsuarios();
		int maxId = 0;
		for (Usuario usuario : usuarios) {
			int id = usuario.getId();

			if (id > maxId) {
				maxId = id;
			}
		}
		return maxId + 1;
	}

}

package br.com.alura.spring.data.service;

import java.util.Optional;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.alura.spring.data.orm.Cargo;
import br.com.alura.spring.data.repository.CargoRepository;

@Service
public class CrudCargoService {
	
	private final CargoRepository cargoRepository;
	private Boolean system = true; 

	public CrudCargoService(CargoRepository cargoRepository) {
		this.cargoRepository = cargoRepository;
	}
	
	public void inicial(Scanner scanner) {
		while(system) {
			System.out.println();
			System.out.println("Qual ação de cargo você quer executar?");
			System.out.println("0 - Sair");
			System.out.println("1 - Salvar");
			System.out.println("2 - Atualizar");
			System.out.println("3 - Visualizar");
			System.out.println("4 - Deletado");
			System.out.println();
			int action = scanner.nextInt();	
			if(action == 1) {
				salvar(scanner);
			}
			else if(action == 2) {
				atualizar(scanner);
			}
			else if(action == 3) {
				visualizar();
			}
			else if(action == 4) {
				deletar(scanner);
			}else {
				system = false;
			}
		}
	}
	
	private void deletar(Scanner scanner) {
		System.out.println();
		System.out.println("Escolha o id referente ao cargo que deseja deletar");
		Integer id = scanner.nextInt();
		this.cargoRepository.deleteById(id);
		System.out.println("Deletado");
	}

	private void visualizar() {
		System.out.println();
		System.out.println("Lista de todos os cargos");
		Iterable<Cargo> cargos = cargoRepository.findAll();
		cargos.forEach(cargo -> System.out.println(cargo));
	}

	private void salvar(Scanner scanner) {
		System.out.println();
		System.out.println("Digite o nome do cargo");
		String descricao = scanner.next();
		Cargo cargo = new Cargo();
		cargo.setDescricao(descricao);
		this.cargoRepository.save(cargo);
		System.out.println("Salvo");
	}

	private void atualizar(Scanner scanner) {
		System.out.println();
		System.out.println("Escolha o id referente ao cargo que deseja atualizar");
		Integer id = scanner.nextInt();
		Optional<Cargo> cargo = this.cargoRepository.findById(id);
		if(!cargo.isEmpty()) {
			System.out.println("Digite o nome do cargo");
			String descricao = scanner.next();
			cargo.get().setDescricao(descricao);
			this.cargoRepository.save(cargo.get());
			System.out.println("Atualizado");
		}	
	}
}

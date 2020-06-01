package br.senai.sp.jandira.odonto.resource;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.senai.sp.jandira.odonto.model.Dentista;
import br.senai.sp.jandira.odonto.repository.DentistaRepository;
import br.senai.sp.jandira.odonto.upload.FileBaseStorageService;
import br.senai.sp.jandira.odonto.upload.FileUpload;
import br.senai.sp.jandira.odonto.upload.FileUploadUrl;

@RestController
@RequestMapping("/odonto")
public class DentistaResource {
	
	@Autowired
	private DentistaRepository dentistaRepository;
	
	@Autowired
	private FileBaseStorageService firebase;
	
	@GetMapping("/dentistas")
	public List<Dentista> getDentistas(){
		return dentistaRepository.findAll();
	}
	
	@GetMapping("/dentistas/{codigo}")
	public ResponseEntity<?> getDentista(@PathVariable Long codigo){
		
		Optional<?> dentistaProcurado = dentistaRepository.findById(codigo);

		return dentistaProcurado.isPresent() ? 
				ResponseEntity.ok(dentistaProcurado.get()) : 
				ResponseEntity.notFound().build();
	}
	
	@PostMapping("/dentistas")
	@ResponseStatus(HttpStatus.CREATED)
	public Dentista gravar(@Valid @RequestBody Dentista dentista) {
		System.out.println("************** " + dentista);
		return dentistaRepository.save(dentista);
	}
	
	@PostMapping("/upload")
	public ResponseEntity<FileUploadUrl> gravarFoto(@RequestBody FileUpload file) {
		
		FileUploadUrl url = new FileUploadUrl(firebase.upload(file));
		
		return ResponseEntity.ok(url);
	}
	
	@DeleteMapping("/dentistas/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable Long codigo) {
		dentistaRepository.deleteById(codigo);
	}
	
	@PutMapping("/dentistas")
	public void atualizar(@Valid @RequestBody Dentista dentista) {
		dentistaRepository.save(dentista);
		System.out.println(dentistaRepository.save(dentista));
	}

}

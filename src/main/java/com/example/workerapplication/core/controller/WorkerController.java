package com.example.workerapplication.core.controller;

import com.example.workerapplication.converter.WorkerDtoToWorkerConverter;
import com.example.workerapplication.converter.WorkerToWorkerDtoConverter;
import com.example.workerapplication.dto.WorkerDto;
import com.example.workerapplication.model.Worker;
import com.example.workerapplication.repository.WorkerRepository;
import com.example.workerapplication.validators.BankAccountValidator;
import com.example.workerapplication.validators.PeselValidator;
import com.example.workerapplication.customerservice.WorkerService;
import feign.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class WorkerController {

    @Autowired
    private WorkerRepository workerRepository;

    @Autowired
    private WorkerToWorkerDtoConverter workerToWorkerDtoConverter;
    @Autowired
    private WorkerDtoToWorkerConverter workerDtoToWorkerConverter;
    @Autowired
    private WorkerService workerService;

    @PostMapping("/worker")
    public ResponseEntity<?> createWorker(@RequestBody WorkerDto workerDto) {
        Worker worker = new Worker();
        worker.setName(workerDto.getName());
        worker.setSurname(workerDto.getSurname());

        if (workerRepository.existsByPESEL(workerDto.getPESEL())) {
            throw new RuntimeException(" Worker is exist");
        }
        if (!PeselValidator.isValidPesel(workerDto.getPESEL())) {
            throw new RuntimeException(" Pesel is not correct ");
        }
        worker.setPESEL(workerDto.getPESEL());


        if (workerRepository.existsByAccountNumber(workerDto.getAccountNumber())) {
            throw new RuntimeException(" Number Account is exist");
        }
        if (!BankAccountValidator.isValidBankAccountNumber(workerDto.getAccountNumber())) {
            throw new RuntimeException(" Number Account is not correct ");
        }
        worker.setAccountNumber(workerDto.getAccountNumber());

        worker.setWorkerType(workerDto.getWorkerType());


        if ("B2B" .equals(workerDto.getWorkerType())) {
            String NIP = workerDto.getNIP();
            if (NIP == null || NIP.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("NIP is required for B2B type");
//                throw new RuntimeException(" NIP is required for B2B type ");
            }
        }
        worker.setNIP(workerDto.getNIP());
        workerRepository.save(worker);
        return ResponseEntity.ok().body(workerDto);
    }

    @PutMapping("worker/{id}")
    public WorkerDto editWorker(@RequestBody WorkerDto workerDto, @PathVariable Long id) {
        Worker worker = workerDtoToWorkerConverter.convert(workerDto);
        worker = workerService.editWorker(worker, id);
        return workerToWorkerDtoConverter.convert(worker);
    }

    @GetMapping("worker/{id}")
    public WorkerDto getWorkerDto(@PathVariable Long id) {

        return workerToWorkerDtoConverter.convert(workerService.getWorker(id));
    }


//        Worker worker = workerRepository.getReferenceById(id);
//        worker.setName(workerDto.getName());
//        worker.setSurname(workerDto.getSurname());
//        worker.setPESEL(workerDto.getPESEL());
//        worker.setAccountNumber(workerDto.getAccountNumber());
//        worker.setWorkerType(workerDto.getWorkerType());
//        worker.setNIP(workerDto.getNIP());
//        workerRepository.save(worker);
//        return workerDto;


    //        @PutMapping("human/{id}")
//        public HumanDto putHuman(@RequestBody HumanDto humanDto, @PathVariable Long id) {
//            Human human = humanDtoToHumanConverter.convert(humanDto);
//            human = humanService.editHuman(human, id);
//            return humanToHumanDtoConverter.convert(human);
//        }
//    }
    @GetMapping("/worker/page")
    public Page<Worker> getWorkerPage() {

//        Pageable firstPageWithTenElements = PageRequest.of(0, 9);
//        Pageable secondPageWithTenElements = PageRequest.of(10, 19);
//        List<Part> secondPart = partRepository.findAllByPartDescription(10L);
//        Page<Part> firstPart = partRepository.findAll(firstPageWithTenElements);
//        Page<Part> sort = partRepository.findAll(Sort.by("part_description"));

        PageRequest pageable = PageRequest.of(0, 10, Sort.by("name")); // Pierwsza strona, 10 rekordów na stronę
        return workerRepository.findAll(pageable);
    }

    @GetMapping("/worker/pesel/{PESEL}")
    public WorkerDto getWorkerPESEL(@PathVariable String PESEL) {
        WorkerDto workerDto = new WorkerDto();

        Worker worker = workerRepository.findByPESEL(PESEL);

        workerDto.setName(worker.getName());
        workerDto.setSurname(worker.getSurname());
        workerDto.setPESEL(worker.getPESEL());
        workerDto.setAccountNumber(worker.getAccountNumber());
        workerDto.setWorkerType(worker.getWorkerType());
        workerDto.setNIP(worker.getNIP());
        return workerDto;
    }
}

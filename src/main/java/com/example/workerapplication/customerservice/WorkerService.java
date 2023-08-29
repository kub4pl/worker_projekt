package com.example.workerapplication.customerservice;

import com.example.workerapplication.dto.WorkerDto;
import com.example.workerapplication.model.Worker;
import com.example.workerapplication.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class WorkerService {


        @Autowired
        private WorkerRepository workerRepository;

        public WorkerService(WorkerRepository workerRepository) {
            this.workerRepository = workerRepository;
        }


    public Worker editWorker(Worker worker, Long id) {

        Worker worker1 = workerRepository.getById(id);
        worker1.setName(worker.getName());
        worker1.setSurname(worker.getSurname());
        worker1.setPESEL(worker.getPESEL());
        worker1.setAccountNumber(worker.getAccountNumber());
        worker1.setWorkerType(worker.getWorkerType());
        worker1.setNIP(worker.getNIP());
        workerRepository.save(worker1);
        return worker1;
    }

    public Worker getWorker( Long id) {

        Worker worker1 = workerRepository.getById(id);
        return worker1;
    }
}


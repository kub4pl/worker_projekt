package com.example.workerapplication.converter;

import com.example.workerapplication.dto.WorkerDto;
import com.example.workerapplication.model.Worker;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


@Component
public class WorkerDtoToWorkerConverter implements Converter<WorkerDto, Worker> {


        @Override
        public Worker convert(WorkerDto source) {
            Worker worker = new Worker();
            worker.setName(source.getName());
            worker.setSurname(source.getSurname());
            worker.setPESEL(source.getPESEL());
            worker.setAccountNumber(source.getAccountNumber());
            worker.setWorkerType(source.getWorkerType());
            worker.setNIP(source.getNIP());
            return worker;
        }
    }



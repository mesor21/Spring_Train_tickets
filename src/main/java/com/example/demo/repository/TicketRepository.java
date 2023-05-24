package com.example.demo.repository;

import com.example.demo.entity.Ticket;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Repository
public class TicketRepository {
    private final String filePlace = "src/main/resources/static/tickets.json";
    private Gson gson;

    private Comparator<Ticket> idComparator = new Comparator<Ticket>() {
        @Override
        public int compare(Ticket o1, Ticket o2) {
            return o1.getId().compareTo(o2.getId());
        }
    };

    public TicketRepository(Gson gson) {
        this.gson = gson;
    }
    @Async
    private List<Ticket> loadData() {
        var list = new ArrayList<Ticket>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filePlace));
            list = gson.fromJson(bufferedReader, new TypeToken<List<Ticket>>() {
            }.getType());
            bufferedReader.close();
            System.out.println("Lighting objects have been read from " + filePlace + " file.");
            list.sort(idComparator);
            return list;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Async
    private void writeData(List<Ticket> tickets) {
        try {
            FileWriter fileWriter = new FileWriter(filePlace);
            gson.toJson(tickets, fileWriter);
            fileWriter.close();
            System.out.println("Lighting objects have been saved to " + filePlace + " file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Async
    public Ticket getByID(Long id) {
        List<Ticket> employee = loadData();
        var buff = employee.stream().filter(x -> x.getId() == Integer.parseInt(id.toString())).findFirst().get();
        return buff;
    }
    @Async
    public void delete(Long myClassId) {
        List<Ticket> myClassList = loadData();
        myClassList.removeIf(x -> myClassId - 1 >= 0 && x.getId() == myClassId);
        writeData(myClassList);
    }
    @Async
    public void save(Ticket employee) {
        List<Ticket> myClassList = loadData();
        if (myClassList.isEmpty()) {
            employee.setId(Long.valueOf(1));
        } else {
            employee.setId(Long.valueOf(myClassList.get(myClassList.size() - 1).getId() + 1));
        }
        myClassList.add(employee);
        writeData(myClassList);
    }
    @Async
    public List<Ticket> findAll() {
        List<Ticket> myClassList = loadData();
        return myClassList;
    }
    @Async
    public Ticket update(Ticket employee) {
        List<Ticket> employees = loadData();
        if (!employees.isEmpty() && employee != null) {
            var id = 0;
            for (var item : employees) {
                if (item.getId() == employee.getId()) {
                    break;
                }
                id = id + 1;
            }
            employees.set(id, employee);
        }
        writeData(employees);
        employees = loadData();
        return employees.stream().filter(x -> (x.getId()) == employee.getId()).toList().get(0);
    }
}

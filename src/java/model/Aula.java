package model;

import java.sql.Time;
import java.util.Date;

public class Aula {

    private int idAula;
    private String materia;
    private Date dataInicio;
    private Date dataFim;
    private Time horario;
    private int qtdAula;
    private Curso curso;

    public int getIdAula() {
        return idAula;
    }

    public void setIdAula(int idAula) {
        this.idAula = idAula;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public Time getHorario() {
        return horario;
    }

    public void setHorario(Time horario) {
        this.horario = horario;
    }

    public int getQtdAula() {
        return qtdAula;
    }

    public void setQtdAula(int qtdAula) {
        this.qtdAula = qtdAula;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Aula() {
    }
    
}

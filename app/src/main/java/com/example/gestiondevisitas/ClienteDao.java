package com.example.gestiondevisitas;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;

public class ClienteDao {

    DBHelper DB;

    public ClienteDao(Context c) {
        DB = new DBHelper(c);
    }

    public void insertar(String numeroDocumento, String nombres, String apellidoPaterno, String apellidoMaterno, String telefono, String comentarios) throws DAOException {
        Log.i("ClienteDao", "insertar()");
        SQLiteDatabase db = DB.getWritableDatabase();
        try {
            String[] args = new String[]{numeroDocumento, nombres,apellidoPaterno,apellidoMaterno,telefono,comentarios};
            db.execSQL("INSERT INTO clientes(numeroDocumento, nombres,apellidoPaterno,apellidoMaterno,telefono,comentarios) VALUES(?,?)", args);
            Log.i("ClienteDao", "Se insertó");
        } catch (Exception e) {
            throw new DAOException("ClienteDao: Error al insertar: " + e.getMessage());
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }

    public ClienteEntity obtener() throws DAOException {
        Log.i("ClienteDao", "obtener()");
        SQLiteDatabase db = DB.getReadableDatabase();
        ClienteEntity cliente = new ClienteEntity();
        try {
            Cursor c = db.rawQuery("select numeroDocumento, nombres, apellidoPaterno,apellidoMaterno,telefono,comentarios from clientes", null);
            if (c.getCount() > 0) {
                c.moveToFirst();
                do {
                    int numeroDocumento = c.getInt(c.getColumnIndex("numeroDocumento"));
                    String nombres = c.getString(c.getColumnIndex("nombres"));
                    String apellidoPaterno = c.getString(c.getColumnIndex("apellidoPaterno"));
                    String apellidoMaterno = c.getString(c.getColumnIndex("apellidoMaterno"));
                    String telefono = c.getString(c.getColumnIndex("telefono"));
                    String comentarios = c.getString(c.getColumnIndex("comentarios"));



                    cliente.setNumeroDocumento(numeroDocumento);
                    cliente.setNombres(nombres);
                    cliente.setApellidoPaterno(apellidoPaterno);
                    cliente.setApellidoMaterno(apellidoMaterno);
                    cliente.setTelefono(telefono);
                    cliente.setComentarios(comentarios);

                } while (c.moveToNext());
            }
            c.close();
        } catch (Exception e) {
            throw new DAOException("ClienteDao: Error al obtener: " + e.getMessage());
        } finally {
            if (db != null) {
                db.close();
            }
        }
        return cliente;
    }

    public ArrayList<ClienteEntity> buscar(String criterio) throws DAOException {
        Log.i("ClienteDao", "buscar()");
        SQLiteDatabase db = DB.getReadableDatabase();
        ArrayList<ClienteEntity> lista = new ArrayList<ClienteEntity>();
        try {
            Cursor c = db.rawQuery("select numeroDocumento, nombres, apellidoPaterno,apellidoMaterno,telefono,comentarios from clientes where apellidoPaterno like '%"+criterio+"%' or apellidoMaterno like '%"+criterio+"%'", null);

            if (c.getCount() > 0) {
                c.moveToFirst();
                do {
                    int numeroDocumento = c.getInt(c.getColumnIndex("numeroDocumento"));
                    String nombres = c.getString(c.getColumnIndex("nombres"));
                    String apellidoPaterno = c.getString(c.getColumnIndex("apellidoPaterno"));
                    String apellidoMaterno = c.getString(c.getColumnIndex("apellidoMaterno"));
                    String telefono = c.getString(c.getColumnIndex("telefono"));
                    String comentarios = c.getString(c.getColumnIndex("comentarios"));


                    ClienteEntity cliente = new ClienteEntity();

                    cliente.setNumeroDocumento(numeroDocumento);
                    cliente.setNombres(nombres);
                    cliente.setApellidoPaterno(apellidoPaterno);
                    cliente.setApellidoMaterno(apellidoMaterno);
                    cliente.setTelefono(telefono);
                    cliente.setComentarios(comentarios);

                    lista.add(cliente);
                } while (c.moveToNext());
            }
            c.close();
        } catch (Exception e) {
            throw new DAOException("ClienteDAO: Error al obtener: " + e.getMessage());
        } finally {
            if (db != null) {
                db.close();
            }
        }
        return lista;
    }

    public void eliminar(int numeroDocumento) throws DAOException {
        Log.i("ClienteDAO", "eliminar()");
        SQLiteDatabase db = DB.getWritableDatabase();

        try {
            String[] args = new String[]{String.valueOf(numeroDocumento)};
            db.execSQL("DELETE FROM clientes WHERE numeroDocumento=?", args);
        } catch (Exception e) {
            throw new DAOException("ClienteDAO: Error al eliminar: " + e.getMessage());
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }




}

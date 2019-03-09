/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entidades.Movimientoinventario;
import entidades.ayudantes.DetalleCotizacionAyudente;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Arco02
 */
@Stateless
public class MovimientoinventarioFacade extends AbstractFacade<Movimientoinventario> {

    @PersistenceContext(unitName = "MediusSoftwareV3PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MovimientoinventarioFacade() {
        super(Movimientoinventario.class);
    }

    public int lastId() {
        try {
            Query query = em.createNativeQuery("SELECT IDMOVIMIENTO FROM `movimientoinventario` ORDER BY IDMOVIMIENTO DESC LIMIT 1");

            return Integer.parseInt(query.getSingleResult().toString());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public void crearMovimientoInventario(Movimientoinventario m, List<DetalleCotizacionAyudente> detalles, String tipo) {
        try {
            String sql = "INSERT INTO movimientoinventario (FUENTE, DESCRIPCION, FECHAMOVIMIENTO, NOTAS, ESTADO, BODEGAID, BODEGADESTINOID) VALUES (?, ?, ?, ?, ?, ?, ?)";

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

            em.createNativeQuery(sql)
                    .setParameter(1, m.getFuente())
                    .setParameter(2, m.getDescripcion())
                    .setParameter(3, sdf.format(m.getFechamovimiento()))
                    .setParameter(4, m.getNotas())
                    .setParameter(5, m.getEstado())
                    .setParameter(6, m.getBodegaid().getIdbodega())
                    .setParameter(7, m.getBodegadestinoid().getIdbodega())
                    .executeUpdate();

            int id = lastId();

            sql = "INSERT INTO detallemovimiento (movimientoid, insumoid, cantidad) VALUES (?, ?, ?)";

            for (DetalleCotizacionAyudente detalle : detalles) {
                if (detalle.getCantidad() > 0) {
                    em.createNativeQuery(sql)
                            .setParameter(1, id)
                            .setParameter(2, detalle.getInsumoId())
                            .setParameter(3, detalle.getCantidad())
                            .executeUpdate();
                }
            }

            int idBodega1 = m.getBodegaid().getIdbodega();
            Query query;
            int idBodega2;

            switch (tipo) {
                case "ini":
                case "pf":
                    for (DetalleCotizacionAyudente detalle : detalles) {
                        if (detalle.getCantidad() > 0) {
                            bodegaInsumoNoExisten(idBodega1, detalle.getInsumoId());

                            sql = "UPDATE bodegainsumo SET BODEGAINSUMOENTRADA = BODEGAINSUMOENTRADA + ?, BODEGAINSUMOTOTAL = BODEGAINSUMOENTRADA - BODEGAINSUMOSALIDA WHERE BODEGAID = ? AND INSUMOID = ?";

                            query = em.createNativeQuery(sql);
                            query.setParameter(1, detalle.getCantidad());
                            query.setParameter(2, idBodega1);
                            query.setParameter(3, detalle.getInsumoId());

                            query.executeUpdate();
                        }
                    }
                    break;
                case "sb":
                case "sc":
                    for (DetalleCotizacionAyudente detalle : detalles) {
                        if (detalle.getCantidad() > 0) {
                            bodegaInsumoNoExisten(idBodega1, detalle.getInsumoId());

                            sql = "UPDATE bodegainsumo SET BODEGAINSUMOSALIDA = BODEGAINSUMOSALIDA + ?, BODEGAINSUMOTOTAL = BODEGAINSUMOENTRADA - BODEGAINSUMOSALIDA WHERE BODEGAID = ? AND INSUMOID = ?";

                            query = em.createNativeQuery(sql);
                            query.setParameter(1, detalle.getCantidad());
                            query.setParameter(2, idBodega1);
                            query.setParameter(3, detalle.getInsumoId());

                            query.executeUpdate();
                        }
                    }
                case "tb":
                    for (DetalleCotizacionAyudente detalle : detalles) {
                        if (detalle.getCantidad() > 0) {
                            idBodega2 = m.getBodegadestinoid().getIdbodega();
                            
                            bodegaInsumoNoExisten(idBodega1, detalle.getInsumoId());
                            bodegaInsumoNoExisten(idBodega2, detalle.getInsumoId());

                            sql = "UPDATE bodegainsumo SET BODEGAINSUMOSALIDA = BODEGAINSUMOSALIDA + ?, BODEGAINSUMOTOTAL = BODEGAINSUMOENTRADA - BODEGAINSUMOSALIDA WHERE BODEGAID = ? AND INSUMOID = ?";
                            query = em.createNativeQuery(sql);
                            query.setParameter(1, detalle.getCantidad());
                            query.setParameter(2, idBodega1);
                            query.setParameter(3, detalle.getInsumoId());
                            
                            query.executeUpdate();
                            
                            sql = "UPDATE bodegainsumo SET BODEGAINSUMOENTRADA = BODEGAINSUMOENTRADA + ?, BODEGAINSUMOTOTAL = BODEGAINSUMOENTRADA - BODEGAINSUMOSALIDA WHERE BODEGAID = ? AND INSUMOID = ?";
                            query = em.createNativeQuery(sql);
                            query.setParameter(1, detalle.getCantidad());
                            query.setParameter(2, idBodega2);
                            query.setParameter(3, detalle.getInsumoId());

                            query.executeUpdate();
                        }
                    }

                    break;
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void bodegaInsumoNoExisten(int idBodega, int idInsumo) {
        String sql = "SELECT COUNT(*) FROM bodegainsumo WHERE BODEGAID = ? AND INSUMOID = ?";

        Query query = em.createNativeQuery(sql);
        query.setParameter(1, idBodega);
        query.setParameter(2, idInsumo);

        int count = Integer.parseInt(query.getSingleResult().toString());

        if (count == 0) {
            sql = "INSERT INTO bodegainsumo (BODEGAID, INSUMOID, BODEGAINSUMOENTRADA, BODEGAINSUMOSALIDA, BODEGAINSUMOTOTAL) VALUES (?, ?, 0, 0, 0)";

            query = em.createNativeQuery(sql);
            query.setParameter(1, idBodega);
            query.setParameter(2, idInsumo);

            query.executeUpdate();
        }
    }
}

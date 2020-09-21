/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visao;

import java.awt.Color;
import javax.swing.JOptionPane;
import modelo.dao.GenericoDAO;
import modelo.dao.HibernateUtil;
import modelo.vo.DespesaPessoal;
import org.hibernate.Session;

/**
 *
 * @author marcos-victor
 */
public class EditarDespesaPessoal extends javax.swing.JPanel {

    private DespesaPessoal despesa;
    private ListarDespesaPessoal listarDespesa;
    private GenericoDAO<DespesaPessoal> genericoDes;

    public EditarDespesaPessoal(DespesaPessoal despesa, ListarDespesaPessoal listarDespesa) {
        initComponents();
        this.despesa = despesa;
        this.listarDespesa = listarDespesa;
        this.atualizar.setOpaque(true);
        this.atualizar.setBackground(Color.white);
        this.atualizar.setForeground(Color.black);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        atualizar = new javax.swing.JButton();
        descricao = new javax.swing.JTextField();
        valor = new javax.swing.JFormattedTextField();
        dataEmissao = new javax.swing.JFormattedTextField();

        atualizar.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        atualizar.setText("Atualizar");
        atualizar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                atualizarMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                atualizarMouseEntered(evt);
            }
        });
        atualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                atualizarActionPerformed(evt);
            }
        });

        descricao.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Descrição", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.BELOW_TOP, new java.awt.Font("Dialog", 1, 12))); // NOI18N

        valor.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Valor", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.BELOW_TOP, new java.awt.Font("Dialog", 1, 12))); // NOI18N
        valor.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));

        dataEmissao.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Data de Emissao", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.BELOW_TOP, new java.awt.Font("Dialog", 1, 12))); // NOI18N
        dataEmissao.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter()));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(descricao)
                            .addComponent(valor)
                            .addComponent(dataEmissao, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(82, 82, 82)
                        .addComponent(atualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(52, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(descricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(valor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(dataEmissao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addComponent(atualizar)
                .addContainerGap(56, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void atualizarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_atualizarMouseExited
        this.atualizar.setBackground(Color.white);
        this.atualizar.setForeground(Color.black);
    }//GEN-LAST:event_atualizarMouseExited

    private void atualizarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_atualizarMouseEntered
        this.atualizar.setBackground(Color.red);
        this.atualizar.setForeground(Color.white);
    }//GEN-LAST:event_atualizarMouseEntered

    private void atualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_atualizarActionPerformed
        if (this.descricao.equals("") || this.valor.equals("") || this.dataEmissao.equals("")) {
            JOptionPane.showMessageDialog(null, "Complete todos os campos", "Atenção", JOptionPane.WARNING_MESSAGE);
        } else {
            this.despesa.setDescricao(descricao.getText());
            this.despesa.setValor(Float.parseFloat(valor.getText().replace(",", ".")));
            this.despesa.setDataEmissao(dataEmissao.getText());

            Session sessao = HibernateUtil.getSessionFactory().openSession();
            this.genericoDes = new GenericoDAO<DespesaPessoal>(DespesaPessoal.class, sessao);

            this.genericoDes.update(despesa);
            sessao.close();
            listarDespesa.filtrarPorDescricao();
            JOptionPane.showMessageDialog(null, "Atualizado", "Informação", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_atualizarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton atualizar;
    private javax.swing.JFormattedTextField dataEmissao;
    private javax.swing.JTextField descricao;
    private javax.swing.JFormattedTextField valor;
    // End of variables declaration//GEN-END:variables
}

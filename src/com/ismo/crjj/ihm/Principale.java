package com.ismo.crjj.ihm;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.exemple.Utils.DoubleValidate;
import com.ismo.crjj.metier.IMetier;
import com.ismo.crjj.metier.MetierProduit;
import com.ismo.crjj.model.Produit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;

public class Principale extends JFrame implements ActionListener {

	enum Mode {
		ADD,
		UPDATE,
		NORMAL
	}
	private JPanel contentPane;
	IMetier<Produit> metier = new MetierProduit();
	private JTextField txt_code;
	private JTextField txt_lib;
	private JTextField txt_achat;
	private JTextField txt_vente;
	List<Produit> mesProduits;
	int pos = 0;
	private JButton btn_first;
	private JButton btn_prev;
	private JButton btn_next;
	private JButton btn_last;
	private JButton btnAjouter;
	private JButton btnModifier;
	private JButton btnSupprimer;
	private JButton btnFermer;
	Mode mode = Mode.NORMAL;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principale frame = new Principale();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Principale() {

		setTitle("Fiche Produit");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 710, 377);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Fiche produit");
		lblNewLabel.setBorder(new LineBorder(new Color(255, 128, 0), 2, true));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(187, 25, 346, 63);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Code produit : ");
		lblNewLabel_1.setBounds(40, 147, 119, 14);
		contentPane.add(lblNewLabel_1);

		txt_code = new JTextField();
		txt_code.setEnabled(false);
		txt_code.setBounds(169, 141, 86, 20);
		contentPane.add(txt_code);
		txt_code.setColumns(10);

		JLabel lblNewLabel_1_1 = new JLabel("Libelle produit :");
		lblNewLabel_1_1.setBounds(40, 175, 119, 14);
		contentPane.add(lblNewLabel_1_1);

		txt_lib = new JTextField();
		txt_lib.setEnabled(false);
		txt_lib.setColumns(10);
		txt_lib.setBounds(169, 169, 221, 20);
		contentPane.add(txt_lib);

		JLabel lblNewLabel_1_2 = new JLabel("Prix achat : ");
		lblNewLabel_1_2.setBounds(40, 203, 119, 14);
		contentPane.add(lblNewLabel_1_2);

		txt_achat = new JTextField();
		txt_achat.setEnabled(false);
		txt_achat.setColumns(10);
		txt_achat.setBounds(169, 197, 124, 20);
		txt_achat.setDocument(new DoubleValidate());
		contentPane.add(txt_achat);

		JLabel lblNewLabel_1_3 = new JLabel("Prix vente : ");
		lblNewLabel_1_3.setBounds(40, 231, 119, 14);
		contentPane.add(lblNewLabel_1_3);

		txt_vente = new JTextField();
		txt_vente.setEnabled(false);
		txt_vente.setColumns(10);
		txt_vente.setBounds(169, 225, 124, 20);
		txt_vente.setDocument(new DoubleValidate());
		contentPane.add(txt_vente);

		btn_first = new JButton("<<");
		btn_first.setBounds(40, 292, 89, 23);
		btn_first.addActionListener(this);
		contentPane.add(btn_first);

		btn_prev = new JButton("<");
		btn_prev.setBounds(139, 292, 89, 23);
		btn_prev.addActionListener(this);
		contentPane.add(btn_prev);

		btn_next = new JButton(">");
		btn_next.setBounds(238, 292, 89, 23);
		btn_next.addActionListener(this);
		contentPane.add(btn_next);

		btn_last = new JButton(">>");
		btn_last.setBounds(337, 292, 89, 23);
		btn_last.addActionListener(this);
		contentPane.add(btn_last);

		btnAjouter = new JButton("Ajouter");
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (btnAjouter.getText().equalsIgnoreCase("ajouter")) {
					btnAjouter.setText("Enregistrer");
					btnModifier.setText("Annuler");
					activeDesactiveButtons(true);
					btnModifier.setEnabled(true);
					txt_lib.setText("");
					txt_achat.setText("");
					txt_vente.setText("");
					txt_lib.requestFocus();
					
					mode = Mode.ADD;
				} else {
					Produit p = new Produit();

					if(!txt_code.getText().isEmpty()) {
						p.setNumProd(Integer.valueOf(txt_code.getText().substring(1)));
					}
					p.setDescription(txt_lib.getText());
					if(!txt_achat.getText().isEmpty())
						p.setPrixAchat(Double.parseDouble(txt_achat.getText()));
					
					if(!txt_vente.getText().isEmpty())
						p.setPrixVente(Double.parseDouble(txt_vente.getText()));

					boolean res = mode==Mode.ADD ? metier.save(p) : metier.update(p);
					
					if (res) {
						mesProduits = metier.getAll();
												
						if(mode == Mode.ADD) {
							JOptionPane.showMessageDialog(null, "Produit Ajoute avec succes");
							pos = mesProduits.size() - 1;
						}
						else 
							JOptionPane.showMessageDialog(null, "Produit modifie avec succes");
						
						activeDesactiveButtons(false);
						remplirFicheProduit();


					} else {
						activeDesactiveButtons(false);
						mesProduits = metier.getAll();
						remplirFicheProduit();
						JOptionPane.showMessageDialog(null, "Erreur d'ajout");
					}

					btnAjouter.setText("Ajouter");
					btnModifier.setText("Modifier");

					mode=Mode.NORMAL;
				}
			}
		});
		btnAjouter.setBounds(497, 140, 149, 23);
		contentPane.add(btnAjouter);

		btnModifier = new JButton("Modifier");
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(btnModifier.getText().equalsIgnoreCase("annuler")) {
					activeDesactiveButtons(false);
					btnAjouter.setText("Ajouter");
					btnModifier.setText("Modifier");
					mesProduits = metier.getAll();
					remplirFicheProduit();
					
					mode=Mode.NORMAL;
				}else {
					mode= Mode.UPDATE;
					btnAjouter.setText("Enregistrer");
					btnModifier.setText("Annuler");
					activeDesactiveButtons(true);
					btnModifier.setEnabled(true);
					txt_lib.requestFocus();
				}
			}
		});
		btnModifier.setBounds(497, 171, 149, 23);
		contentPane.add(btnModifier);

		btnSupprimer = new JButton("Supprimer");
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int res = JOptionPane.showConfirmDialog(null, "Voulez-vous supprimer ce produit?",
						"Supprimer ce produit", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

				if (res == JOptionPane.YES_OPTION) {
					Produit p = mesProduits.get(pos);
					metier.delete(p);

					JOptionPane.showMessageDialog(null, "Produit supprime avec succes");
					mesProduits = metier.getAll();
					if (pos >= mesProduits.size())
						pos--;

					remplirFicheProduit();
				}
			}
		});
		btnSupprimer.setBounds(497, 199, 149, 23);
		contentPane.add(btnSupprimer);

		btnFermer = new JButton("Fermer");
		btnFermer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnFermer.setBounds(497, 227, 149, 23);
		contentPane.add(btnFermer);

		mesProduits = metier.getAll();
		remplirFicheProduit();
	}

	void remplirFicheProduit() {

		if (pos <= 0) {
			btn_first.setEnabled(false);
			btn_prev.setEnabled(false);
		} else {
			btn_first.setEnabled(true);
			btn_prev.setEnabled(true);
		}

		if (pos >= mesProduits.size() - 1) {
			btn_last.setEnabled(false);
			btn_next.setEnabled(false);
		} else {
			btn_last.setEnabled(true);
			btn_next.setEnabled(true);
		}

		if (mesProduits.size() > 0) {
			txt_code.setText(String.format("P%04d", mesProduits.get(pos).getNumProd()));
			txt_lib.setText(mesProduits.get(pos).getDescription());
			txt_achat.setText(String.format("%.2f", mesProduits.get(pos).getPrixAchat()).replace(',', '.'));
			txt_vente.setText(String.format("%.2f", mesProduits.get(pos).getPrixVente()).replace(',', '.'));

			btnModifier.setEnabled(true);
			btnSupprimer.setEnabled(true);
		} else {
			txt_code.setText("");
			txt_lib.setText("");
			txt_achat.setText("");
			txt_vente.setText("");

			btnModifier.setEnabled(false);
			btnSupprimer.setEnabled(false);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();

		switch (b.getText()) {
		case "<<":
			pos = 0;
			break;
		case "<":
			pos--;
			break;
		case ">":
			pos++;
			break;
		case ">>":
			pos = mesProduits.size() - 1;
			break;
		}

		remplirFicheProduit();
	}
	
	void activeDesactiveButtons(boolean etat) {
		btnSupprimer.setEnabled(!etat);
		txt_lib.setEnabled(etat);
		txt_achat.setEnabled(etat);
		txt_vente.setEnabled(etat);
		btn_first.setEnabled(!etat);
		btn_last.setEnabled(!etat);
		btn_prev.setEnabled(!etat);
		btn_next.setEnabled(!etat);
	}
}

package com.ismo.crjj.ihm;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.ismo.crjj.metier.IMetier;
import com.ismo.crjj.metier.MetierProduit;
import com.ismo.crjj.model.Produit;
import javax.swing.JLabel;
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
		setBounds(100, 100, 710, 383);
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
		txt_code.setBounds(169, 141, 86, 20);
		contentPane.add(txt_code);
		txt_code.setColumns(10);

		JLabel lblNewLabel_1_1 = new JLabel("Libelle produit :");
		lblNewLabel_1_1.setBounds(40, 175, 119, 14);
		contentPane.add(lblNewLabel_1_1);

		txt_lib = new JTextField();
		txt_lib.setColumns(10);
		txt_lib.setBounds(169, 169, 221, 20);
		contentPane.add(txt_lib);

		JLabel lblNewLabel_1_2 = new JLabel("Prix achat : ");
		lblNewLabel_1_2.setBounds(40, 203, 119, 14);
		contentPane.add(lblNewLabel_1_2);

		txt_achat = new JTextField();
		txt_achat.setColumns(10);
		txt_achat.setBounds(169, 197, 124, 20);
		contentPane.add(txt_achat);

		JLabel lblNewLabel_1_3 = new JLabel("Prix vente : ");
		lblNewLabel_1_3.setBounds(40, 231, 119, 14);
		contentPane.add(lblNewLabel_1_3);

		txt_vente = new JTextField();
		txt_vente.setColumns(10);
		txt_vente.setBounds(169, 225, 124, 20);
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
		btnAjouter.setBounds(497, 140, 149, 23);
		contentPane.add(btnAjouter);
		
		btnModifier = new JButton("Modifier");
		btnModifier.setBounds(497, 171, 149, 23);
		contentPane.add(btnModifier);
		
		btnSupprimer = new JButton("Supprimer");
		btnSupprimer.setBounds(497, 199, 149, 23);
		contentPane.add(btnSupprimer);
		
		btnFermer = new JButton("Fermer");
		btnFermer.setBounds(497, 227, 149, 23);
		contentPane.add(btnFermer);

		mesProduits = metier.getAll();
		remplirFicheProduit();
	}

	void remplirFicheProduit() {
		
		if(pos==0) {
			btn_first.setEnabled(false);
			btn_prev.setEnabled(false);
		}else {
			btn_first.setEnabled(true);
			btn_prev.setEnabled(true);
		}
		
		if(pos==mesProduits.size()-1) {
			btn_last.setEnabled(false);
			btn_next.setEnabled(false);
		}else {
			btn_last.setEnabled(true);
			btn_next.setEnabled(true);
		}
		
		txt_code.setText(String.format("P%04d", mesProduits.get(pos).getNumProd()));
		txt_lib.setText(mesProduits.get(pos).getDescription());
		txt_achat.setText(String.format("%.2f", mesProduits.get(pos).getPrixAchat()));
		txt_vente.setText(String.format("%.2f", mesProduits.get(pos).getPrixVente()));
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
}

<?php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use App\Entity\Commande;
use App\Form\CommandeType;
use App\Repository\CommandeRepository;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Serializer\Encoder\JsonEncoder;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer;
use Symfony\Component\Serializer\SerializerInterface;

use Symfony\Component\Serializer\Normalizer\NormalizerInterface;

class MobileController extends AbstractController
{
    /**
     * @Route("/mobile", name="mobile")
     */
    public function index(): Response
    {
        return $this->render('mobile/index.html.twig', [
            'controller_name' => 'MobileController',
        ]);
    }
    /**
     *  @Route("/liste", name="liste")
     * @param CommandeRepository $repository
     */
    public function getCours(Request $request,CommandeRepository  $stadeRepository,NormalizerInterface $normalizer):Response{
        $repo = $stadeRepository->findAll();

        $json = $normalizer->normalize($repo,'json',['groups'=>'commande']);

        return new Response (json_encode($json));

    }

    /**
     * @Route("/add", name="add_commande")
     */
    public function addStades(Request $request){
        $commande= new Commande();
        $nom= $request->query->get("nom");
        $prenom= $request->query->get("prenom");
        $email= $request->query->get("email");
        $adresse= $request->query->get("adresse");
        $numtelephone= $request->query->get("numtelephone");
        $date= $request->query->get("date");
        $totalcost= $request->query->get("totalcost");
        $commande->setNom($nom);
        $commande->setPrenom($prenom);
        $commande->setEmail($email);
        $commande->setAdresse($adresse);
        $commande->setNumtelephone($numtelephone);
        $commande->setDate($date);
        $commande->setTotalcost($totalcost);
        $em=$this->getDoctrine()->getManager();
        $em->persist( $commande);
        $em->flush();
        $serialize = new Serializer([new ObjectNormalizer()]);
        $formatted = $serialize->normalize(" Commande Ajoutée");
        return new JsonResponse($formatted);

    }
    /**
     * @Route("/deletestade", name="deletestade", methods={"GET","POST"})
     *
     */
    public function deleteStade(Request $request){
        $id_stade=$request->get('id_stade');
        $entityManager = $this->getDoctrine()->getManager();
        $stade=$entityManager->getRepository(Stade::class)->find($id_stade);
        if($stade!=null){
            $entityManager->remove($stade);
            $entityManager->flush();
            $serialize = new Serializer([new ObjectNormalizer()]);
            $formatted = $serialize->normalize("Stade deleted");
            return new JsonResponse($formatted);
        }

    }
    /**
     * @Route("/updatestade", name="updatestade", methods={"GET","POST"})
     *
     */
    public function updateStade(Request $request){
        $em=$this->getDoctrine()->getManager();
        $stade=$this->getDoctrine()->getManager()->getRepository(Stade::class)->find($request->get("id_stade"));
        $stade->setNom($request->get("nom"));
        $stade->setAdresse($request->get("adresse"));
        $stade->setPrixph($request->get("prixph"));
        $stade->setContact($request->get("contact"));
        $stade->setImage($request->get("image"));
        $em->persist($stade);
        $em->flush();
        $serialize = new Serializer([new ObjectNormalizer()]);
        $formatted = $serialize->normalize("Stade updated");
        return new JsonResponse($formatted);
    }


    /**
     * @Route("/detail/{commandeid}", name="detailstade")
     *
     */
    public function Commandedetail(Request $request,CommandeRepository $commandeRepository,SerializerInterface $serializerinterface,$commandeid)
    {
        $repo = $commandeRepository->find($commandeid);
        $json = $serializerinterface->serialize($repo,'json',['groups'=>'stade']);

        return new Response (json_encode($json));

    }
}
